package hu.schonherz.jee.web.view;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.schonherz.jee.service.client.api.service.user.UserServiceRemote;
import hu.schonherz.jee.service.client.api.vo.UserVo;

@ManagedBean(name = "registrationView")
@ViewScoped
public class RegistrationView {

	@EJB
	private UserServiceRemote serviceRemote;

	private UserVo userVo;

	@PostConstruct
	public void init() {
		userVo = new UserVo();
	}

	public void registration() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			UserVo check = serviceRemote.findByUsername(userVo.getUsername());
			if (check != null) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", "Usernem alredyuse!"));

			} else {
				
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				String encryptedPass = bCryptPasswordEncoder.encode(userVo.getPassword());
				userVo.setPassword(encryptedPass);
				
				serviceRemote.registrationUser(userVo);
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Successsssss!", "Registration!"));
			}
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", "Registration!"));

		}
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public UserServiceRemote getServiceRemote() {
		return serviceRemote;
	}

	public void setServiceRemote(UserServiceRemote serviceRemote) {
		this.serviceRemote = serviceRemote;
	}
}
