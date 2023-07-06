package tn.camps.tuncamps.controller.forum;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistence.entity.forum.Post;
import tn.camps.tuncamps.service.forum.IPostService;

import java.util.List;

@RestController
@RequestMapping("/post")
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
    @GetMapping("/show/{id}")
    public  Post postById(int id){
        return  iPostService.retrievePost(id);
    }

    @PutMapping("update/{id}")
    public Post updatePost(@PathVariable("id") int id, @RequestBody Post post) {
        return iPostService.updatePost(id, post);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable("id") int id) {
        iPostService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
