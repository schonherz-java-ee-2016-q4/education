package hu.neuron.java.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.neuron.java.core.entity.User;
import hu.neuron.java.service.vo.UserVO;

public class UserConverter {

	private static Mapper mapper = new DozerBeanMapper();

	public static UserVO toVO(User dto) {
		if (dto == null) {
			return null;
		}

		return mapper.map(dto, UserVO.class);
	}

	public static List<UserVO> toVO(List<User> dtos) {
		if (dtos == null) {
			return null;
		}
		List<UserVO> vos = new ArrayList<UserVO>();
		for (User dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public static User toEntity(UserVO vo) {
		if (vo == null) {
			return null;
		}

		return mapper.map(vo, User.class);
	}

	public static List<User> toEntity(List<UserVO> vos) {
		if (vos == null) {
			return null;
		}
		List<User> dtos = new ArrayList<User>();
		for (UserVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}
}
