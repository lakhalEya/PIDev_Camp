package tn.camps.tuncamps.service.forum;

import tn.camps.tuncamps.persistence.entity.forum.CommunitySpace;
import java.util.List;

public interface ICommunitySpace {
    CommunitySpace createCommunitySpace(CommunitySpace communitySpace);
    CommunitySpace retrieveCommunitySpace(int id);
    List<CommunitySpace> retrieveAllCommunitySpace();
   /* CommunitySpace updateCommunitySpace(int id, CommunitySpace communitySpace);*/
    CommunitySpace updateCommunitySpace(CommunitySpace communitySpace);
    void deleteCommunitySpace(int id);
}
