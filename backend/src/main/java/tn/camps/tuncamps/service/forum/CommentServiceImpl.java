package tn.camps.tuncamps.service.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entity.forum.Comment;
import tn.camps.tuncamps.persistence.repository.forum.CommentRepository;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService
{
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment retrieveComment(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public List<Comment> retrieveAllComment() {
        List<Comment> listComment = (List<Comment>) commentRepository.findAll();
        return listComment;
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        if(commentRepository.existsById(comment.getIdComment())) {
            Comment c = commentRepository.findById(comment.getIdComment()).get();
            c.setContent(comment.getContent());
            c.setDatePublication(comment.getDatePublication());
            commentRepository.save(comment);
        }
        return comment;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
