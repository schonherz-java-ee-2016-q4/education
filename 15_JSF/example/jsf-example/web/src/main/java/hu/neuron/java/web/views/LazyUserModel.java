package hu.neuron.java.web.views;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import hu.neuron.java.core.entity.Gender;
import hu.neuron.java.service.UserService;
import hu.neuron.java.service.exception.ServiceException;
import hu.neuron.java.service.vo.UserVO;

public class LazyUserModel extends LazyDataModel<UserVO> {

	private static final long serialVersionUID = 1L;

	private UserService userService = null;
	private List<UserVO> visibleUserList;

	public LazyUserModel() {

	}

	public LazyUserModel(UserService userService) {
		this.userService = userService;

	}

	@Override
	public UserVO getRowData(String rowkey) {
		if (visibleUserList != null || rowkey != null) {
			for (UserVO user : visibleUserList) {
				if (user.getId().toString().equals(rowkey)) {
					return user;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(UserVO user) {
		if (user == null) {
			return null;
		}
		return user.getId();
	}

	@Override
	public List<UserVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		String filter = "";
		
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		if (sortField == null) {
			sortField = "userName";
		}

		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		try {
			visibleUserList = userService.getUserList(first / pageSize, pageSize, sortField, dir, filter,
					filterColumnName);
			Long dataSize = userService.getUserCount();
			this.setRowCount(dataSize.intValue());
		} catch (ServiceException e) {
			e.printStackTrace();
			this.setRowCount(0);
		}

		return visibleUserList;
	}

	public List<UserVO> getVisibleUserList() {
		return visibleUserList;
	}

	public void setVisibleUserList(List<UserVO> visibleUserList) {
		this.visibleUserList = visibleUserList;
	}

}
