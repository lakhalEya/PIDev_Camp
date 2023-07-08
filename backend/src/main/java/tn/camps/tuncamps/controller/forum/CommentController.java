package tn.camps.tuncamps.controller.forum;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.dto.CommentDTO;
import tn.camps.tuncamps.mapper.MyObjectMapper;
import tn.camps.tuncamps.persistence.entity.forum.Comment;
import tn.camps.tuncamps.service.forum.ICommentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins ="http://localhost:4200")
public class CommentController {
    private final ICommentService iCommentService;

    private final MyObjectMapper mapper;

    public CommentController(ICommentService iCommentService, MyObjectMapper mapper) {
        this.iCommentService = iCommentService;
        this.mapper = mapper;
    }

    @PostMapping("/addComment")
    @ResponseBody
    public ResponseEntity<Long> createComment(@RequestBody CommentDTO commentDto){
        try{
            Comment comment = iCommentService.createComment(mapper.toComment(commentDto));
            return ResponseEntity.status(HttpStatus.CREATED).body(comment.getIdComment());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("show")
    public ResponseEntity<List<CommentDTO>> retrieveAllComment(){

        List<CommentDTO>  commentDTOS = new ArrayList<>();
        List<Comment> comments = iCommentService.retrieveAllComment();
        if(null!=comments && !comments.isEmpty()){
            for (Comment comment:comments
            ) {
                try{
                    commentDTOS.add(mapper.toCommentDto(comment));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(commentDTOS);
        }

        return ResponseEntity.status(HttpStatus.OK).body(commentDTOS);

    }
    @GetMapping("/show/{id}")
    public Comment retrieveComment(@PathVariable("id") Long id){
        return  iCommentService.retrieveComment(id);
    }
    @PutMapping("update/{id}")
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
