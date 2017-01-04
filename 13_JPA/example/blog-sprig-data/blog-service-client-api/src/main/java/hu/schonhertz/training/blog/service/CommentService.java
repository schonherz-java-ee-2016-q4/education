package hu.schonhertz.training.blog.service;

import hu.schonhertz.training.blog.vo.CommentVo;

public interface CommentService {

	public void addCommnet(CommentVo commentVo, Long blogId);
}
