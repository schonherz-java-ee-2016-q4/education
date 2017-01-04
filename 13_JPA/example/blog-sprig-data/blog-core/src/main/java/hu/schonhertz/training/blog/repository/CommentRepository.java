package hu.schonhertz.training.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonhertz.training.blog.entity.Comment;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
