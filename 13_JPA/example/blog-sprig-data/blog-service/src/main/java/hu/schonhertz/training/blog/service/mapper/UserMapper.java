package hu.schonhertz.training.blog.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonhertz.training.blog.entity.User;
import hu.schonhertz.training.blog.vo.UserVo;

public class UserMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static UserVo toVo(User blogDto) {
		if (blogDto == null) {
			return null;
		}
		return mapper.map(blogDto, UserVo.class);
	}

	public static User toDto(UserVo blogVo) {
		if (blogVo == null) {
			return null;
		}
		return mapper.map(blogVo, User.class);
	}

	public static List<UserVo> toVo(List<User> blogDtos) {
		List<UserVo> blogVos = new ArrayList<>();
		for (User blogDto : blogDtos) {
			blogVos.add(toVo(blogDto));
		}
		return blogVos;
	}

	public static List<User> toDto(List<UserVo> blogVos) {
		List<User> blogDtos = new ArrayList<>();
		for (UserVo blogVo : blogVos) {
			blogDtos.add(toDto(blogVo));
		}
		return blogDtos;
	}

}
