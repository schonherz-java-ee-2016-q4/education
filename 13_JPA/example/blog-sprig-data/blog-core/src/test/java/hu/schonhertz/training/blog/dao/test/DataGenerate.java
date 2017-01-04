package hu.schonhertz.training.blog.dao.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import hu.schonhertz.training.blog.entity.Blog;
import hu.schonhertz.training.blog.entity.Comment;
import hu.schonhertz.training.blog.entity.Role;
import hu.schonhertz.training.blog.entity.User;
import hu.schonhertz.training.blog.repository.BlogRepository;
import hu.schonhertz.training.blog.repository.CommentRepository;
import hu.schonhertz.training.blog.repository.RoleRepository;
import hu.schonhertz.training.blog.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-core-test.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@Rollback(false)
public class DataGenerate {

	private static final Logger logger = Logger.getLogger(DataGenerate.class);

	@Autowired
	UserRepository userDao;

	@Autowired
	RoleRepository roleDao;

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	CommentRepository commentRepository;

	@Test
	public void test1GenerateUsers() {
		try {
			Role userRole = roleDao.findRoleByName("ROLE_USER");
			if (userRole == null) {
				userRole = new Role();
				userRole.setName("ROLE_USER");
				roleDao.save(userRole);
			}
			ArrayList<User> users = new ArrayList<>();

			JsonReader jsonReader = new JsonReader();
			JSONObject json = jsonReader.readJsonFromUrl("https://randomuser.me/api/?results=20");
			JSONArray array = json.getJSONArray("results");
			for (int i = 0; i < array.length(); i++) {
				JSONObject jsonObject = array.getJSONObject(i);
				RandomUser randomUser = new RandomUser(jsonObject.getJSONObject("user"));

				User user = new User();
				user.setUserName(randomUser.getUsername());
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

				user.setRoles(new ArrayList<>());
				user.getRoles().add(userRole);

				users.add(user);
				logger.info("Create user: " + user.getUserName());

			}
			userDao.save(users);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2GenerateBlogs() {
		List<Blog> blogs = new ArrayList<>();
		List<User> users = userDao.findAll();

		for (int i = 0; i < 10; i++) {
			try {
				int r = (new Random().nextInt(users.size() - 1));
				User user = users.get(r);

				Blog blog = new Blog();
				blog.setCreateDate(new Date());
				blog.setTitle("Blog " + i);
				blog.setCreator(user);

				URL url = new URL("https://baconipsum.com/api/?type=all-meat&paras=10&start-with-lorem=1&format=html");
				InputStream in = url.openStream();
				StringWriter writer = new StringWriter();

				IOUtils.copy(in, writer);
				IOUtils.closeQuietly(in);
				IOUtils.closeQuietly(writer);
				blog.setText(writer.toString());

				url = new URL("https://placeimg.com/900/300/any");
				in = url.openStream();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				IOUtils.copy(in, out);
				IOUtils.closeQuietly(in);
				IOUtils.closeQuietly(out);

				blog.setImage(out.toByteArray());
				blogs.add(blog);

				logger.info("Create blog: " + blog.getTitle());

			} catch (Exception e) {
				logger.error(e.getMessage(), e);

			}
			blogRepository.save(blogs);
		}

	}

	@Test
	public void test3GenerateComments() {
		List<Blog> savecomments = new ArrayList<>();
		try {

			List<User> users = userDao.findAll();
			List<Blog> blogs = blogRepository.findAll();
			for (int i = 0; i < 100; i++) {

				int r = (new Random().nextInt(users.size() - 1));
				User user = users.get(r);

				int r2 = (new Random().nextInt(blogs.size() - 1));
				Blog blog = blogs.get(r2);
				Comment comment = new Comment();
				comment.setCreator(user);
				comment.setCreateDate(new Date());
				URL url = new URL("https://baconipsum.com/api/?type=all-meat&paras=2&start-with-lorem=1&format=html");
				InputStream in = url.openStream();
				StringWriter writer = new StringWriter();

				IOUtils.copy(in, writer);
				IOUtils.closeQuietly(in);
				IOUtils.closeQuietly(writer);
				comment.setComment(writer.toString());

				Set<Comment> comments = blog.getComments();

				if (comments == null) {
					blog.setComments(new HashSet<>());
				}
				blog.getComments().add(comment);
				savecomments.add(blog);
				logger.info("Create comments: " + blog.getTitle() + " " + user.getUserName());

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

		blogRepository.save(savecomments);
	}

}
