package hu.schonhertz.training.blog.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonhertz.training.blog.entity.Comment;
import hu.schonhertz.training.blog.vo.CommentVo;

public class CommentMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static CommentVo toVo(Comment commentDto) {
		if (commentDto == null) {
			return null;
		}
		return mapper.map(commentDto, CommentVo.class);
	}

	public static Comment toDto(CommentVo commentVo) {
		if (commentVo == null) {
			return null;
		}
		return mapper.map(commentVo, Comment.class);
	}

	public static List<CommentVo> toVo(List<Comment> commentDtos) {
		List<CommentVo> commentVos = new ArrayList<>();
		for (Comment commentDto : commentDtos) {
			commentVos.add(toVo(commentDto));
		}
		return commentVos;
	}

	public static List<Comment> toDto(List<CommentVo> commentVos) {
		List<Comment> commentDtos = new ArrayList<>();
		for (CommentVo commentVo : commentVos) {
			commentDtos.add(toDto(commentVo));
		}
		return commentDtos;
	}

}
