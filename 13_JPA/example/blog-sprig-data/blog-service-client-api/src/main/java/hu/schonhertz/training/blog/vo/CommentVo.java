package hu.schonhertz.training.blog.vo;

import java.io.Serializable;
import java.util.Date;

public class CommentVo implements Serializable {

	private static final long serialVersionUID = -4877641302795620514L;

	private Long id;
	private String comment;
	private UserVo creator;
	private Date createDate;

	public CommentVo() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

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

	public UserVo getCreator() {
		return creator;
	}

	public void setCreator(UserVo creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
