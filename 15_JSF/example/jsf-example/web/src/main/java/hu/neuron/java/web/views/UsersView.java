package hu.neuron.java.web.views;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import hu.neuron.java.service.UserService;

@ViewScoped
@ManagedBean(name = "usersView")
public class UsersView implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyUserModel userModel;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@PostConstruct
	public void init() {
		setUserModel(new LazyUserModel(userService));
	}

	public LazyUserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(LazyUserModel userModel) {
		this.userModel = userModel;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
