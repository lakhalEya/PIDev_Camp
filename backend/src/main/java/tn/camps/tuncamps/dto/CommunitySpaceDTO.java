package tn.camps.tuncamps.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.camps.tuncamps.persistence.entity.enumeration.CommunityCategory;
import tn.camps.tuncamps.persistence.entity.forum.Post;

import javax.persistence.*;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommunitySpaceDTO {
    private int idForum;
    private String title;
    private String description;
    private CommunityCategory category;
    private Set<PostDTO> posts;
    private byte[] fileData;
    private String fileName;
    private String fileType;
}
