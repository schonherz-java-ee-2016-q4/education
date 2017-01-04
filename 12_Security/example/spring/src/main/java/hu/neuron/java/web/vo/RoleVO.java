package hu.neuron.java.web.vo;

import java.io.Serializable;

public class RoleVO implements Serializable {

	private static final long serialVersionUID = -8403753397412273249L;

	private String name;

	public RoleVO(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
