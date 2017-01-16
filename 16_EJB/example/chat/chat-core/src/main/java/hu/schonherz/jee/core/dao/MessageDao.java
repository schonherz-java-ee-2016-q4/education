package hu.schonherz.jee.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.jee.core.entity.Message;
import hu.schonherz.jee.core.entity.User;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface MessageDao extends JpaRepository<Message, Long> {

	List<Message> findByFromUserAndToUserOrFromUserAndToUserOrderByrecDateDesc(User from,User to,User ansFrom,User ansTo);
	

}
