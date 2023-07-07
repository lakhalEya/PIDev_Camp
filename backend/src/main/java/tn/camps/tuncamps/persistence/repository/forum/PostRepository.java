package tn.camps.tuncamps.persistence.repository.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistence.entity.forum.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
//    @Modifying
//    @Query("UPDATE Post p SET p.communitySpace.idForum = :communitySpaceId WHERE p.idPost = :postId")
//    void setCommunitySpaceId(@Param("postId") int postId, @Param("communitySpaceId") int communitySpaceId);

}
