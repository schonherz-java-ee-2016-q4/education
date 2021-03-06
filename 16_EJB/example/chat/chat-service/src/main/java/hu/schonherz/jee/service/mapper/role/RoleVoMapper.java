package hu.schonherz.jee.service.mapper.role;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.jee.core.entity.Role;
import hu.schonherz.jee.service.client.api.vo.RoleVo;

public class RoleVoMapper {

	static Mapper mapper = new DozerBeanMapper();

	public static RoleVo toVo(Role Role) {
		if (Role == null) {
			return null;
		}
		return mapper.map(Role, RoleVo.class);
	}

	public static Role toEntity(RoleVo RoleVo) {
		if (RoleVo == null) {
			return null;
		}
		return mapper.map(RoleVo, Role.class);
	}

	public static List<RoleVo> toVo(List<Role> Role) {
		List<RoleVo> rv = new ArrayList<>();
		for (Role Roles : Role) {
			rv.add(toVo(Roles));
		}
		return rv;
	}

	public static List<Role> toEntity(List<RoleVo> Role) {
		List<Role> rv = new ArrayList<>();
		for (RoleVo Roles : Role) {
			rv.add(toEntity(Roles));
		}
		return rv;
	}
}
