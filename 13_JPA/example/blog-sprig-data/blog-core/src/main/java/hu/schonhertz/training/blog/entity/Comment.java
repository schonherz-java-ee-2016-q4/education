package hu.schonhertz.training.blog.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class User.
 */
@Entity
@Table(name = "Comment")
public class Comment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private User creator;

	@Lob
	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
