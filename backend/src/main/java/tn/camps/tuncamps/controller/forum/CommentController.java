package tn.camps.tuncamps.controller.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistence.entity.forum.Comment;
import tn.camps.tuncamps.service.forum.ICommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;

    @PostMapping("/addComment")
    @ResponseBody
    public Comment createComment(@RequestBody Comment comment){
        return  iCommentService.createComment(comment);
    }
    @GetMapping("show")
    public List<Comment> retrieveAllComment(){
        return iCommentService.retrieveAllComment();
    }
    @GetMapping("/show/{id}")
    public Comment retrieveComment(@PathVariable("id") Long id){
        return  iCommentService.retrieveComment(id);
    }
    @PutMapping("update")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Long id, @RequestBody Comment comment){
        Comment c = iCommentService.updateComment(id, comment);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id){
        iCommentService.deleteComment(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
