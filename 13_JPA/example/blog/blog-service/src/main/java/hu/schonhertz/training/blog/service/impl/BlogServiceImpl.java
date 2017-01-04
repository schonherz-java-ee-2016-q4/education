package hu.schonhertz.training.blog.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonhertz.training.blog.dao.BlogDao;
import hu.schonhertz.training.blog.dto.BlogDto;
import hu.schonhertz.training.blog.service.BlogService;
import hu.schonhertz.training.blog.service.mapper.BlogMapper;
import hu.schonhertz.training.blog.vo.BlogVo;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BlogServiceImpl implements BlogService {

	static final Logger logger = LogManager.getLogger(BlogServiceImpl.class.getName());

	@Autowired
	private BlogDao blogDao;

	@Override
	public List<BlogVo> getAllBlog() {
		List<BlogDto> blogDtos = null;
		try {
			blogDtos = blogDao.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return BlogMapper.toVo(blogDtos);
	}

	@Override
	public BlogVo getBlogById(Integer id) {
		BlogDto blogDto = null;
		try {
			blogDto = blogDao.find(id.longValue());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return BlogMapper.toVo(blogDto);
	}

	@Override
	public void createBlog(BlogVo blogVo) {
		try {
			blogDao.save(BlogMapper.toDto(blogVo));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
