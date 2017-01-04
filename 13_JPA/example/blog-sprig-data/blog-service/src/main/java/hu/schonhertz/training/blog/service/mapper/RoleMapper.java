package hu.schonhertz.training.blog.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonhertz.training.blog.entity.Role;
import hu.schonhertz.training.blog.vo.RoleVo;

public class RoleMapper {

	private static Mapper mapper = new DozerBeanMapper();

	public static RoleVo toVo(Role blogDto) {
		if (blogDto == null) {
			return null;
		}
		return mapper.map(blogDto, RoleVo.class);
	}

	public static Role toDto(RoleVo blogVo) {
		if (blogVo == null) {
			return null;
		}
		return mapper.map(blogVo, Role.class);
	}

	public static List<RoleVo> toVo(List<Role> blogDtos) {
		List<RoleVo> blogVos = new ArrayList<>();
		for (Role blogDto : blogDtos) {
			blogVos.add(toVo(blogDto));
		}
		return blogVos;
	}

	public static List<Role> toDto(List<RoleVo> blogVos) {
		List<Role> blogDtos = new ArrayList<>();
		for (RoleVo blogVo : blogVos) {
			blogDtos.add(toDto(blogVo));
		}
		return blogDtos;
	}

}
