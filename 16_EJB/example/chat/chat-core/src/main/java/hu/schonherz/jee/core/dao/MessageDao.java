package hu.schonherz.jee.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.jee.core.entity.Message;
import hu.schonherz.jee.core.entity.User;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface MessageDao extends JpaRepository<Message, Long> {
	@Query("select m from Message m where ( m.toUser = :to and m.fromUser = :from) or (m.toUser = :from and m.fromUser = :to) order by m.recDate")
	List<Message> findMessage(@Param("from") User from, @Param("to") User to);

}
