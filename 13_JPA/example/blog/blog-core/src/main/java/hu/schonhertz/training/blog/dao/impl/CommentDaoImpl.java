package hu.schonhertz.training.blog.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonhertz.training.blog.dao.CommentDao;
import hu.schonhertz.training.blog.dto.CommentDto;
import hu.schonhertz.training.blog.entity.Comment;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class CommentDaoImpl extends BaseDaoImpl<Comment, CommentDto>
		implements BaseConvertDao<Comment, CommentDto>, CommentDao {

	@Override
	public Comment toEntity(CommentDto dto, Comment entity) {
		Comment ret = entity;
		if (dto.getId() == null || entity == null) {
			ret = new Comment();

		}
		ret.setId(dto.getId());
		ret.setComment(dto.getComment());

		return ret;
	}

	@Override
	public CommentDto toDto(Comment entity) {
		CommentDto ret = new CommentDto();
		if (entity == null) {
			return null;
		}
		ret.setId(entity.getId());
		ret.setComment(entity.getComment());

		return ret;
	}

}
