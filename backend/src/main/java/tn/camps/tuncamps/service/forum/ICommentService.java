package tn.camps.tuncamps.service.forum;

import tn.camps.tuncamps.persistence.entity.forum.Comment;
import java.util.List;

public interface ICommentService {
    Comment createComment(Comment comment);
    Comment retrieveComment(Long id);
    List<Comment> retrieveAllComment();
    Comment updateComment(Long id, Comment comment);
    void deleteComment(Long id);
}
