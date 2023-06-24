package tn.camps.tuncamps.persistence.entity.forum;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.camps.tuncamps.persistence.entity.enumeration.CommunityCategory;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommunitySpace implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idForum;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private CommunityCategory category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy ="communitySpace", fetch = FetchType.EAGER)
    private List<Post> Posts = new ArrayList<>();

}
