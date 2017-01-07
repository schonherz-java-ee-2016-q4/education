package hu.neuron.java.service.converter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class GenericConverter<E, V> {

	private Mapper mapper = new DozerBeanMapper();

	private Class<V> voClass = null;
	private Class<E> entityClass = null;

	public GenericConverter(Class<E> entityClass, Class<V> voClass) {
		super();
		this.voClass = voClass;
		this.entityClass = entityClass;
	}

	public V toVO(E e) {
		if (e == null) {
			return null;
		}
		return mapper.map(e, voClass);
	}

	public Set<V> toVO(Collection<E> dtos) {
		if (dtos == null) {
			return null;
		}
		Set<V> vos = new LinkedHashSet<V>();
		for (E dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public E toEntity(V vo) {
		if (vo == null) {
			return null;
		}

		return mapper.map(vo, entityClass);
	}

	public Set<E> toEntity(List<V> vos) {
		if (vos == null) {
			return null;
		}
		Set<E> dtos = new LinkedHashSet<E>();
		for (V vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}

}
