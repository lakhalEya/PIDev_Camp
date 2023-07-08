package tn.camps.tuncamps.controller.forum;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.dto.CommunitySpaceDTO;
import tn.camps.tuncamps.dto.PostDTO;
import tn.camps.tuncamps.mapper.MyObjectMapper;
import tn.camps.tuncamps.persistence.entity.forum.CommunitySpace;
import tn.camps.tuncamps.persistence.entity.forum.Post;
import tn.camps.tuncamps.service.forum.IPostService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins ="http://localhost:4200")
public class PostController {
    private final IPostService iPostService;
    private final MyObjectMapper mapper;

   public PostController(IPostService iPostService, MyObjectMapper mapper) {
       this.iPostService = iPostService;
       this.mapper = mapper;
    }


    @PostMapping("/addPost")
    public ResponseEntity<Integer> createPost(@RequestBody PostDTO postDTO) {
       try{
           Post createdPost = iPostService.createPost(mapper.toPost(postDTO));
           return ResponseEntity.status(HttpStatus.CREATED).body(createdPost.getIdPost());
       }catch (Exception e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }

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
    public ResponseEntity<List<PostDTO>> listPost(){

        List<PostDTO>  postDTOS = new ArrayList<>();
        List<Post> posts = iPostService.retrieveAllPost();
        if(null!=posts && !posts.isEmpty()){
            for (Post post:posts
            ) {
                try{
                    postDTOS.add(mapper.toPostDto(post));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(postDTOS);
        }

        return ResponseEntity.status(HttpStatus.OK).body(postDTOS);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<PostDTO> retreivePostById(@PathVariable("id") int id){
       Post post = iPostService.retrievePost(id);
       if(null!=post){
           try{
               return ResponseEntity.status(HttpStatus.OK).body(mapper.toPostDto(post));
           } catch (Exception e) {
               e.printStackTrace();
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
           }
       }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }

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
