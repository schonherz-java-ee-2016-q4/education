package hu.schonhertz.training.blog.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonhertz.training.blog.entity.Blog;
import hu.schonhertz.training.blog.entity.Comment;
import hu.schonhertz.training.blog.repository.BlogRepository;
import hu.schonhertz.training.blog.repository.CommentRepository;
import hu.schonhertz.training.blog.service.CommentService;
import hu.schonhertz.training.blog.service.mapper.CommentMapper;
import hu.schonhertz.training.blog.vo.CommentVo;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CommentServiceImpl implements CommentService {

	static final Logger logger = LogManager.getLogger(CommentServiceImpl.class.getName());

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	BlogRepository blogRepository;

	@Override
	public void addCommnet(CommentVo commentVo, Long blogId) {
		try {
			Blog blog = blogRepository.findOne(blogId);

			Set<Comment> comments = blog.getComments();

			if (comments == null) {
				blog.setComments(new HashSet<>());
			}
			blog.getComments().add(CommentMapper.toDto(commentVo));

			blogRepository.save(blog);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
