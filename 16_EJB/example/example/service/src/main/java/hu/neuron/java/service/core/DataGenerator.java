package hu.neuron.java.service.core;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import hu.schonherz.java.dao.RoleDao;
import hu.schonherz.java.dao.UserDao;
import hu.schonherz.java.entities.Role;
import hu.schonherz.java.entities.User;

@Component
public class DataGenerator {

	private static final Logger logger = Logger.getLogger(DataGenerator.class);

	private static List<String> positions = new ArrayList<String>() {
		{
			add("Territory Sales Manager");
			add("B2B sales");
			add("B2c sales");

		}
	};

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	public void generateUsers() {
		try {

			ArrayList<User> users = new ArrayList<>();

			JsonReader jsonReader = new JsonReader();
			JSONObject json = jsonReader.readJsonFromUrl("https://randomuser.me/api/0.8/?results=20");
			JSONArray array = json.getJSONArray("results");
			for (int i = 0; i < array.length(); i++) {
				JSONObject jsonObject = array.getJSONObject(i);
				RandomUser randomUser = new RandomUser(jsonObject.getJSONObject("user"));

				User user = new User();
				user.setUsername(randomUser.getUsername());
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				String encPassword = bCryptPasswordEncoder.encode("password");

				user.setPassword(encPassword);
				URL url = new URL(randomUser.getPictureMedium());
				BufferedImage bufferedImage = ImageIO.read(url);

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bufferedImage, "jpg", baos);
				baos.flush();
				byte[] data = baos.toByteArray();
				baos.close();

				user.setImage(data);

				Integer position = (int) (Math.random() * positions.size());

				users.add(user);
				logger.debug(user.getUsername());
			}

			for (User user : users) {
				Role userRole = roleDao.findByName("ROLE_USER");
				user.setRoles(new ArrayList<Role>());
				user.getRoles().add(userRole);
			}
			users = (ArrayList<User>) userDao.save(users);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
