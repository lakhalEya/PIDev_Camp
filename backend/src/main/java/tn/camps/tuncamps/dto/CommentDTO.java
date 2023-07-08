package tn.camps.tuncamps.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.camps.tuncamps.persistence.entity.forum.Post;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long idComment;
    private String content;
    private Date datePublication;
    private int postId;
}
