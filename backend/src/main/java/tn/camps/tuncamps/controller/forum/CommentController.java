package tn.camps.tuncamps.controller.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.camps.tuncamps.persistence.entity.forum.Comment;
import tn.camps.tuncamps.persistence.entity.forum.CommunitySpace;
import tn.camps.tuncamps.service.forum.ICommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;
    @PostMapping("/add")
    @ResponseBody
    public Comment createComment(@RequestBody Comment comment) {
        return iCommentService.createComment(comment);
    }

}
