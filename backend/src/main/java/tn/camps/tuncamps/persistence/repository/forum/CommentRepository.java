package tn.camps.tuncamps.persistence.repository.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistence.entity.forum.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
