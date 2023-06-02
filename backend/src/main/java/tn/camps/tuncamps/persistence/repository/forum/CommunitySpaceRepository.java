package tn.camps.tuncamps.persistence.repository.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.camps.tuncamps.persistence.entity.forum.CommunitySpace;
@Repository
public interface CommunitySpaceRepository  extends JpaRepository<CommunitySpace, Integer> {
}
