package hu.neuron.java.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.neuron.java.core.entity.Gender;
import hu.neuron.java.core.entity.User;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface UserDao extends JpaRepository<User, Long> {

	User findUserByName(@Param("userName") String name) throws Exception;

	Page<User> findByUserNameStartsWith(String filter,Pageable pageable);
	
	Page<User> findByGender(Gender filter,Pageable pageable);
	
	Page<User> findByEmailStartsWith(String filter,Pageable pageable);
	@Query("select u from User u where lower(u.firstName) like ?1% or lower(u.lastName) like ?1% ")
	Page<User> findByFirstNameOrLastNameStartingWith(String filter,Pageable pageable);

}
