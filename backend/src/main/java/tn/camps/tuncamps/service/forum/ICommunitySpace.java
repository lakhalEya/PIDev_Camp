package tn.camps.tuncamps.service.forum;

import org.springframework.web.multipart.MultipartFile;
import tn.camps.tuncamps.persistence.entity.enumeration.CommunityCategory;
import tn.camps.tuncamps.persistence.entity.forum.CommunitySpace;
import tn.camps.tuncamps.persistence.entity.forum.Post;

import java.io.IOException;
import java.util.List;

public interface ICommunitySpace {
    CommunitySpace createCommunitySpace(CommunitySpace communitySpace);
    CommunitySpace retrieveCommunitySpace(int id);
    List<CommunitySpace> retrieveAllCommunitySpace();
    CommunitySpace updateCommunitySpace(int id, CommunitySpace communitySpace);
    void deleteCommunitySpace(int id);
    List<CommunitySpace> searchCommunitySpaces(String keyword);
    CommunitySpace uploadCommunitySpace(MultipartFile file) throws IOException;



//    List<CommunitySpace> getAllCommunitySpacesWithPosts();
//    CommunitySpace addCommunitySpaceWithPosts(CommunitySpace communitySpace);
//    void  createCommunitySpace(CommunitySpace communitySpace, Post post);
}
