package hu.schonherz.jee.service.client.api.vo;

import java.io.Serializable;
import java.util.Date;

public class BaseVo implements Serializable {

	private static final long serialVersionUID = -2768001384876339734L;

	private Long id;

	private String recUser;

	private String modUser;

	private Date recDate;

	private Date modDate;

	public BaseVo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecUser() {
		return recUser;
	}

	public void setRecUser(String recUser) {
		this.recUser = recUser;
	}

	public String getModUser() {
		return modUser;
	}

	public void setModUser(String modUser) {
		this.modUser = modUser;
	}

	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

}
