package tn.camps.tuncamps.service.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
//    @Transactional
//    @Override
//    public Post createPost(Post post, int postId) {
//        // Add any additional logic before saving the post
//
//        Post createdPost = postRepository.save(post);
//        postRepository.setpostId(createdPost.getIdPost(), postId);
//
//        return createdPost;
//    }
//    public List<Post> getPostsBypostId(int postId) {
//        return postRepository.findBypostId(postId);
//    }

    @Override
    public Post retrievePost(int id) {
        return postRepository.findById(id).orElse(null);
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
            return postRepository.save(existingPost);
        }
        return null;
    }


    @Override
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }
}

