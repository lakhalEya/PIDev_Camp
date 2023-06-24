package tn.camps.tuncamps.controller.forum;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistence.entity.forum.Post;
import tn.camps.tuncamps.service.forum.IPostService;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private IPostService iPostService;

    @PostMapping("/add")
    @ResponseBody
    public Post createPost(@RequestBody Post post){
        return iPostService.createPost(post);
    }
    @GetMapping("/show")
    public List<Post> listPost(){
        return  iPostService.retrieveAllPost();
    }
}
