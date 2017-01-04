package hu.schonhertz.training.blog.vo;

import java.io.Serializable;
import java.util.List;

public class BlogSearchResult implements Serializable {

	private static final long serialVersionUID = -5478463424729861848L;

	private List<BlogVo> blogVos;

	private Long totalElement;

	public BlogSearchResult() {
	}

	public BlogSearchResult(List<BlogVo> blogVos, Long totalElement) {
		super();
		this.blogVos = blogVos;
		this.totalElement = totalElement;
	}

	public List<BlogVo> getBlogVos() {
		return blogVos;
	}

	public void setBlogVos(List<BlogVo> blogVos) {
		this.blogVos = blogVos;
	}

	public Long getTotalElement() {
		return totalElement;
	}

	public void setTotalElement(Long totalElement) {
		this.totalElement = totalElement;
	}

}
