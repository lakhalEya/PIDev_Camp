package tn.camps.tuncamps.persistence.repository.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistence.entity.forum.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
