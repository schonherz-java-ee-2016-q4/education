package hu.schonhertz.training.blog.repository;

public interface RoleRepositoryCustom {

	void removeRoleFromUser(Long roleId, Long userId) throws Exception;

}
