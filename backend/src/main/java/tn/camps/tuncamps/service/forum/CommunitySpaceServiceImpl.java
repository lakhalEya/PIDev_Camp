package tn.camps.tuncamps.service.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.camps.tuncamps.persistence.entity.forum.CommunitySpace;
import tn.camps.tuncamps.persistence.entity.forum.Post;
import tn.camps.tuncamps.persistence.repository.forum.CommunitySpaceRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

  @Service
  public class CommunitySpaceServiceImpl implements ICommunitySpace
    {
        @Autowired
    private CommunitySpaceRepository communitySpaceRepository;

        @Override
        public CommunitySpace createCommunitySpace(CommunitySpace communitySpace) {
            return communitySpaceRepository.save(communitySpace);
        }

//        @Override
//        public void  createCommunitySpace(CommunitySpace communitySpace, Post post) {
//            communitySpace.addPost(post);
//             communitySpaceRepository.save(communitySpace);
//        }
        @Override
        public CommunitySpace uploadCommunitySpace(MultipartFile file) throws IOException {
          CommunitySpace communitySpace = new CommunitySpace();
            try {
                communitySpace.setFileData(file.getBytes());
                communitySpace.setFileName(file.getOriginalFilename());
                communitySpace.setFileType(file.getContentType());

                return communitySpaceRepository.save(communitySpace);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
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


        public List<CommunitySpace> searchCommunitySpaces(String keyword) {
            return communitySpaceRepository.searchByKeyword(keyword);
        }

    }

