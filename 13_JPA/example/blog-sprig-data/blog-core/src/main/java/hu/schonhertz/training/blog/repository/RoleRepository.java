package hu.schonhertz.training.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonhertz.training.blog.entity.Role;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryCustom {

	@Query("select roles from User u join u.roles roles where u.id=?1")
	List<Role> findRolesByUserId(Long userId);

	@Modifying
	@Query(value = "insert into user_role_sw (ROLE_ID, USER_ID) VALUES (?1, ?2)", nativeQuery = true)
	void addRoleToUser(Long roleId, Long userId) throws Exception;

	Role findRoleByName(@Param("roleName") String name) throws Exception;
}
