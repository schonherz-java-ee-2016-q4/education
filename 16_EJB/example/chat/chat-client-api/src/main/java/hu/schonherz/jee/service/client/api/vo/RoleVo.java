package hu.schonherz.jee.service.client.api.vo;

import java.io.Serializable;

public class RoleVo extends BaseVo implements Serializable {

	private static final long serialVersionUID = -4090329349403410228L;

	private String name;

	public RoleVo() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
