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
    public Post updatePost(Post post) {
        if(postRepository.existsById(post.getIdPost())) {
            Post p = postRepository.findById(post.getIdPost()).get();
            p.setContent(post.getContent());
            p.setDatePublication(post.getDatePublication());
            postRepository.save(post);
        }
        return post;
    }

    @Override
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }
}

