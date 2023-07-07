package tn.camps.tuncamps.controller.forum;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.dto.PostDTO;
import tn.camps.tuncamps.mapper.Mapper;
import tn.camps.tuncamps.persistence.entity.forum.CommunitySpace;
import tn.camps.tuncamps.persistence.entity.forum.Post;
import tn.camps.tuncamps.service.forum.IPostService;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final IPostService iPostService;
//    private final Mapper mapper;
//
//    public PostController(IPostService iPostService, Mapper mapper) {
//        this.iPostService = iPostService;
//        this.mapper = mapper;
//    }

    public PostController(IPostService iPostService) {
        this.iPostService = iPostService;
    }

    @PostMapping("/addPost")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = iPostService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }
//    @PostMapping("/createPost")
//    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestParam int communitySpaceId) {
//        Post createdPost = iPostService.createPost(post, communitySpaceId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
//    }

//    @PostMapping("/add")
//    @ResponseBody
//    public ResponseEntity createPost(@RequestBody PostDTO postDTO) throws Exception {
//
//        try {
//            Post post = mapper.toPost(postDTO);
//            iPostService.createPost(post);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @GetMapping("/show")
    public List<Post> listPost(){
        return  iPostService.retrieveAllPost();
    }

    @GetMapping("/show/{id}")
    public Post retreivePostById(@PathVariable("id") int id){
        return  iPostService.retrievePost(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Post> updateDocument(@PathVariable int id, @RequestBody Post post) {
        Post newPost = iPostService.updatePost(id, post);
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable("id") int id) {
        iPostService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
