package hu.neuron.java.web.controllers;

import java.io.Serializable;
import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import hu.neuron.java.service.UserService;
import hu.neuron.java.service.exception.ServiceException;
import hu.neuron.java.service.vo.UserVO;

@ManagedBean(name = "profileController")
@ViewScoped
public class ProfileController implements Serializable {

	private static final long serialVersionUID = 7430801820340921193L;

	private UserVO userVO;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@PostConstruct
	public void init() {

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		Principal principal = req.getUserPrincipal();
		if (principal != null) {
			String userName = principal.getName();
			try {
				userVO = userService.findUserByName(userName);
			} catch (ServiceException e) {

			}
		}

	}

	public void save() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			userService.saveUser(userVO);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes!", "Succes upadte"));
		} catch (ServiceException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error"));
		}
	}

	public void save(UserVO userVO) {
		try {
			userService.saveUser(userVO);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
