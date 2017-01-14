package hu.schonherz.jee.service.client.api.service.role;

import hu.schonherz.jee.service.client.api.vo.RoleVo;

public interface RoleService {

	RoleVo findByName(String name);

	RoleVo save(RoleVo name);
}
