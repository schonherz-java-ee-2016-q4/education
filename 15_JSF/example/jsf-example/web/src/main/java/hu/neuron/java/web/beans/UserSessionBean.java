package hu.neuron.java.web.beans;

import java.io.Serializable;
import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@SessionScoped
@ManagedBean(name = "userSessionBean")
public class UserSessionBean implements Serializable {

	private static final long serialVersionUID = -5959611823743979912L;

	private Boolean authenticated;

	private String userName;

	private String theme;

	@PostConstruct
	public void init() {
		theme = "bootstrap";
		
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		Principal principal = req.getUserPrincipal();
		if (principal != null) {
			userName = principal.getName();
		}
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTheme() {

		return theme;
	}

	public void setTheme(String theme) {

		this.theme = theme;
	}
}
