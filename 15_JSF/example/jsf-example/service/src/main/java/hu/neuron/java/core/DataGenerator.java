package hu.neuron.java.core;

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

import hu.neuron.java.core.dao.RoleDao;
import hu.neuron.java.core.dao.UserDao;
import hu.neuron.java.core.entity.Gender;
import hu.neuron.java.core.entity.Role;
import hu.neuron.java.core.entity.User;

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
				user.setUserName(randomUser.getUsername());
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				String encPassword = bCryptPasswordEncoder.encode("password");

				user.setPassword(encPassword);

				user.setEmail(randomUser.getEmail());
				user.setGender(Gender.valueOf(randomUser.getGender().toUpperCase()));
				user.setFirstName(Character.toUpperCase(randomUser.getPersonFirstName().charAt(0))
						+ randomUser.getPersonFirstName().substring(1));
				user.setLastName(Character.toUpperCase(randomUser.getPersonLastName().charAt(0))
						+ randomUser.getPersonLastName().substring(1));

				user.setBirthDate(new Date(Timestamp.valueOf("1987-01-01 00:00:00").getTime()));
				user.setAbout(
						"Lorem ipsum dolor sit amet, eros sed hendrerit, id leo neque et consectetuer donec, fames pellentesque lacus urna erat erat, consectetuer ante et id ipsum justo pretium, hendrerit magna. Et curae et metus");
				URL url = new URL(randomUser.getPictureMedium());
				BufferedImage bufferedImage = ImageIO.read(url);

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bufferedImage, "jpg", baos);
				baos.flush();
				byte[] data = baos.toByteArray();
				baos.close();

				user.setImage(data);

				Integer position = (int) (Math.random() * positions.size());
				user.setPosition(positions.get(position));

				users.add(user);
				logger.debug(user.getFirstName() + " " + user.getLastName());
			}

			for (User user : users) {
				Role userRole = roleDao.findRoleByName("ROLE_USER");
				user.setRoles(new HashSet<Role>());
				user.getRoles().add(userRole);
			}
			users = (ArrayList<User>) userDao.save(users);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
