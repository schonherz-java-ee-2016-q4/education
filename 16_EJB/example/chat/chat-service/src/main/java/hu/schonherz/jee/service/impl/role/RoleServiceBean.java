package hu.schonherz.jee.service.impl.role;

import java.sql.RowIdLifetime;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.jee.core.dao.RoleDao;
import hu.schonherz.jee.core.entity.Role;
import hu.schonherz.jee.service.client.api.service.role.RoleServiceLocal;
import hu.schonherz.jee.service.client.api.service.role.RoleServiceRemote;
import hu.schonherz.jee.service.client.api.vo.RoleVo;
import hu.schonherz.jee.service.mapper.role.RoleVoMapper;

@Stateless(mappedName = "RoleService")
@Remote(RoleServiceRemote.class)
@Local(RoleServiceLocal.class)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RoleServiceBean implements RoleServiceLocal, RoleServiceRemote {
	@Autowired
	RoleDao roleDao;

	@Override
	public RoleVo findByName(String name) {

		Role findByName = roleDao.findByName(name);

		return RoleVoMapper.toVo(findByName);
	}

	@Override
	public RoleVo save(RoleVo name) {
		Role role = roleDao.save(RoleVoMapper.toEntity(name));

		return RoleVoMapper.toVo(role);
	}

}
