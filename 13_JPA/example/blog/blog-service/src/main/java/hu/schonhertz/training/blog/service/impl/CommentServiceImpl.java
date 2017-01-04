package hu.schonhertz.training.blog.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonhertz.training.blog.dao.BlogDao;
import hu.schonhertz.training.blog.dao.CommentDao;
import hu.schonhertz.training.blog.dto.BlogDto;
import hu.schonhertz.training.blog.dto.CommentDto;
import hu.schonhertz.training.blog.service.CommentService;
import hu.schonhertz.training.blog.service.mapper.CommentMapper;
import hu.schonhertz.training.blog.vo.CommentVo;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CommentServiceImpl implements CommentService {

	static final Logger logger = LogManager.getLogger(CommentServiceImpl.class.getName());

	@Autowired
	CommentDao commentDao;

	@Autowired
	BlogDao blogDao;

	@Override
	@Deprecated
	public List<CommentVo> getAllCommentByBlogId(Integer blogId) {
		return null;
	}

	@Override
	public void createComment(CommentVo commentVo) {
		try {
			commentDao.save(CommentMapper.toDto(commentVo));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public void addCommnet(CommentVo commentVo, Integer blogId) {
		try {
			BlogDto blogDto = blogDao.find(blogId.longValue());

			Set<CommentDto> commentDtos = blogDto.getComments();

			if (commentDtos == null) {
				blogDto.setComments(new HashSet<>());
			}
			blogDto.getComments().add(CommentMapper.toDto(commentVo));

			blogDao.update(blogDto);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
