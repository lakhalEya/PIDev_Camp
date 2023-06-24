package tn.camps.tuncamps.service.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entity.forum.CommunitySpace;
import tn.camps.tuncamps.persistence.entity.forum.Post;
import tn.camps.tuncamps.persistence.repository.forum.CommunitySpaceRepository;
import java.util.List;

  @Service
  public class CommunitySpaceServiceImpl implements ICommunitySpace
    {
        @Autowired
    CommunitySpaceRepository communitySpaceRepository;

        @Override
    public CommunitySpace createCommunitySpace(CommunitySpace communitySpace) {
        return communitySpaceRepository.save(communitySpace) ;
    }
    @Override
        public CommunitySpace retrieveCommunitySpace(int id) {
            return communitySpaceRepository.findById(id).orElse(null);
        }
    @Override
    public List<CommunitySpace> retrieveAllCommunitySpace() {
        List<CommunitySpace> listForum =  (List<CommunitySpace>) communitySpaceRepository.findAll();
        return listForum;
    }
    @Override
        public CommunitySpace updateCommunitySpace(int id, CommunitySpace communitySpace) {
            CommunitySpace existingCommunitySpace = communitySpaceRepository.findById(id).orElse(null);
            if (existingCommunitySpace != null) {
                existingCommunitySpace.setTitle(communitySpace.getTitle());
                existingCommunitySpace.setDescription(communitySpace.getDescription());
                existingCommunitySpace.setCategory(communitySpace.getCategory());
                existingCommunitySpace.setPosts(communitySpace.getPosts());
                return communitySpaceRepository.save(existingCommunitySpace);
            }
            return null;
        }
    @Override
        public void deleteCommunitySpace(int id) {
            communitySpaceRepository.deleteById(id);
        }
    }
