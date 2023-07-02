package tn.camps.tuncamps.service.forum;

import tn.camps.tuncamps.persistence.entity.forum.Post;
import java.util.List;

public interface IPostService {

    Post createPost(Post post);
    Post retrievePost(int id);
    List<Post> retrieveAllPost();
    Post updatePost(Post post);
    void deletePost(int id);
}