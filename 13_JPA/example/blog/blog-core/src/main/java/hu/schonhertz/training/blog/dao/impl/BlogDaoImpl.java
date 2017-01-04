package hu.schonhertz.training.blog.dao.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonhertz.training.blog.dao.BlogDao;
import hu.schonhertz.training.blog.dao.CommentDao;
import hu.schonhertz.training.blog.dao.UserDao;
import hu.schonhertz.training.blog.dto.BlogDto;
import hu.schonhertz.training.blog.dto.CommentDto;
import hu.schonhertz.training.blog.dto.UserDto;
import hu.schonhertz.training.blog.entity.Blog;
import hu.schonhertz.training.blog.entity.Comment;
import hu.schonhertz.training.blog.entity.User;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class BlogDaoImpl extends BaseDaoImpl<Blog, BlogDto> implements BaseConvertDao<Blog, BlogDto>, BlogDao {

	@Autowired
	UserDao userDao;

	@Autowired
	CommentDao commentDao;

	@Override
	public Blog toEntity(BlogDto dto, Blog entity) {
		Blog ret = entity;
		if (dto.getId() == null || entity == null) {
			ret = new Blog();

		}
		ret.setId(dto.getId());
		ret.setText(dto.getText());
		ret.setTitle(dto.getTitle());

		BaseConvertDao<User, UserDto> userBaseConvertDao = (BaseConvertDao<User, UserDto>) userDao;
		ret.setCreator(userBaseConvertDao.toEntity(dto.getCreator(), null));

		BaseConvertDao<Comment, CommentDto> commentBaseConvertDao = (BaseConvertDao<Comment, CommentDto>) commentDao;

		Set<CommentDto> commentDtos = dto.getComments();

		if (commentDtos != null && !commentDtos.isEmpty()) {
			Set<Comment> comments = new HashSet<>();
			for (CommentDto commentDto : commentDtos) {
				comments.add(commentBaseConvertDao.toEntity(commentDto, null));
			}

			ret.setComments(comments);
		}

		return ret;

	}

	@Override
	public BlogDto toDto(Blog entity) {
		BlogDto ret = new BlogDto();
		if (entity == null) {
			return null;

		}
		ret.setId(entity.getId());
		ret.setText(entity.getText());
		ret.setTitle(entity.getTitle());

		BaseConvertDao<User, UserDto> userBaseConvertDao = (BaseConvertDao<User, UserDto>) userDao;

		ret.setCreator(userBaseConvertDao.toDto(entity.getCreator()));

		BaseConvertDao<Comment, CommentDto> commentBaseConvertDao = (BaseConvertDao<Comment, CommentDto>) commentDao;

		Set<Comment> comments = entity.getComments();

		if (comments != null && !comments.isEmpty()) {
			Set<CommentDto> commentDtos = new HashSet<>();
			for (Comment comment : comments) {
				commentDtos.add(commentBaseConvertDao.toDto(comment));
			}
			ret.setComments(commentDtos);
		}
		return ret;
	}

}
