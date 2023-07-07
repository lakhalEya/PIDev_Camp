package tn.camps.tuncamps.persistence.repository.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistence.entity.forum.CommunitySpace;
import tn.camps.tuncamps.persistence.entity.forum.Post;

import java.util.List;

@Repository
public interface CommunitySpaceRepository  extends JpaRepository<CommunitySpace, Integer> {
    @Query("SELECT c FROM CommunitySpace c WHERE c.title LIKE %:keyword% OR c.description LIKE %:keyword%")
    List<CommunitySpace> searchByKeyword(@Param("keyword") String keyword);

}
