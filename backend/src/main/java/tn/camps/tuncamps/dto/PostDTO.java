package tn.camps.tuncamps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.camps.tuncamps.persistence.entity.enumeration.CommunityCategory;
import tn.camps.tuncamps.persistence.entity.enumeration.Visibility;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private int idPost;
    private String title;
    private String content;
    private CommunityCategory category;
    private Date datePublication;
    private Visibility visibility;
    private List<CommentDTO> comments;
    private int communitySpaceId;
}
