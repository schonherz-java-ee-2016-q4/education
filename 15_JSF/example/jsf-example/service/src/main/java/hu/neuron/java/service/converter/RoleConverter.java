package hu.neuron.java.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.neuron.java.core.entity.Role;
import hu.neuron.java.service.vo.RoleVO;

public class RoleConverter {

	private static Mapper mapper = new DozerBeanMapper();

	public static RoleVO toVO(Role dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, RoleVO.class);
	}

	public static List<RoleVO> toVO(List<Role> dtos) {
		if (dtos == null) {
			return null;
		}
		List<RoleVO> vos = new ArrayList<RoleVO>();
		for (Role dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public static Role toEntity(RoleVO vo) {
		if (vo == null) {
			return null;
		}

		return mapper.map(vo, Role.class);
	}

	public static List<Role> toEntity(List<RoleVO> vos) {
		if (vos == null) {
			return null;
		}
		List<Role> dtos = new ArrayList<Role>();
		for (RoleVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}

}
