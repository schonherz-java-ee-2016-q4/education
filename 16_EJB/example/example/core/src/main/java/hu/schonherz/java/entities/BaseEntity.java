package hu.schonherz.java.entities;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	String recUser;

	String modUser;
	@Temporal(TemporalType.TIMESTAMP)
	Date recDate;

	@Temporal(TemporalType.TIMESTAMP)
	Date modDate;

	@PrePersist
	public void prePersist() {
		recUser = getUser();
		recDate = new Date();

	}

	private String getUser() {
		String userName;
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof String) {
				userName = (String) principal;
			} else {
				org.springframework.security.core.userdetails.User userPrincipal = (org.springframework.security.core.userdetails.User) principal;
				userName = userPrincipal.getUsername();
			}

		} catch (Exception e) {
			userName = "unknown";
		}
		return userName;
	}

	@PreUpdate
	public void preUpdate() {
		modUser = getUser();
		modDate = new Date();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the recUser
	 */
	public String getRecUser() {
		return recUser;
	}

	/**
	 * @param recUser
	 *            the recUser to set
	 */
	public void setRecUser(String recUser) {
		this.recUser = recUser;
	}

	/**
	 * @return the modUser
	 */
	public String getModUser() {
		return modUser;
	}

	/**
	 * @param modUser
	 *            the modUser to set
	 */
	public void setModUser(String modUser) {
		this.modUser = modUser;
	}

	/**
	 * @return the recDate
	 */
	public Date getRecDate() {
		return recDate;
	}

	/**
	 * @param recDate
	 *            the recDate to set
	 */
	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	/**
	 * @return the modDate
	 */
	public Date getModDate() {
		return modDate;
	}

	/**
	 * @param modDate
	 *            the modDate to set
	 */
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

}
