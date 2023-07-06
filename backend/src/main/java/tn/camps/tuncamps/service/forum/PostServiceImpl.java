package tn.camps.tuncamps.service.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entity.forum.Post;
import tn.camps.tuncamps.persistence.repository.forum.PostRepository;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService{
    @Autowired
    PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post retrievePost(int id) {
        Post post = postRepository.findById(id).get();
        return post;
    }

    @Override
    public List<Post> retrieveAllPost() {
        List<Post> posts =  (List<Post>) postRepository.findAll();
        return posts;
    }

    @Override
    public Post updatePost(int id, Post post) {
        Post existingPost = postRepository.findById(id).orElse(null);
        if (existingPost != null) {
            existingPost.setTitle(post.getTitle());
            existingPost.setCategory(post.getCategory());
            existingPost.setContent(post.getContent());
            existingPost.setDatePublication(post.getDatePublication());
            existingPost.setVisibility(post.getVisibility());
//            existingPost.setCommunitySpace(post.getCommunitySpace());
            return postRepository.save(post);
        }
        return null;
    }


    @Override
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }
}

