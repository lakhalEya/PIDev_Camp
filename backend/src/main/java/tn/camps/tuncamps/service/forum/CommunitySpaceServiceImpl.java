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
    CommunitySpaceRepository communitySpaceRepository;

//        @Override
//    public CommunitySpace addCommunitySpaceWithPosts(CommunitySpace communitySpace) {
//            // Create a new CommunitySpace entity
//            CommunitySpace communitySpace1 = new CommunitySpace();
//            communitySpace1.setTitle(communitySpace.getTitle());
//            communitySpace1.setDescription(communitySpace.getDescription());
//            communitySpace1.setCategory(communitySpace.getCategory());
//
//            // Create and associate Post entities
//            List<Post> posts = new ArrayList<>();
//            for (Post post : communitySpace.getPosts()) {
//                Post post1 = new Post();
//                post1.setTitle(post.getTitle());
//                post1.setContent(post.getContent());
//                // Set other post properties
////                post1.setCommunitySpace(communitySpace); // Set the relationship with the community space
//                posts.add(post);
//            }
//            communitySpace.setPosts(new HashSet<>(posts));
//
//            // Save the community space and associated posts
//            communitySpaceRepository.save(communitySpace);
//                return communitySpaceRepository.save(communitySpace);
//        }

        @Override
        public CommunitySpace createCommunitySpace(CommunitySpace communitySpace) {
            return communitySpaceRepository.save(communitySpace);
        }
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

