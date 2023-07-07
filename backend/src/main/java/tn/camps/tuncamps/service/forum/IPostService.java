package tn.camps.tuncamps.service.forum;

import tn.camps.tuncamps.persistence.entity.forum.Post;
import java.util.List;

public interface IPostService {

    Post createPost(Post post);

    //    @Override
    //    public Post createPost(Post post) {
    //        return postRepository.save(post);
    //    }
//    Post createPost(Post post, int communitySpaceId);

    Post retrievePost(int id);
    List<Post> retrieveAllPost();
    Post updatePost(int id,Post post);
    void deletePost(int id);

//    List<Post> getPostsByCommunitySpaceId(int communitySpaceId);
}