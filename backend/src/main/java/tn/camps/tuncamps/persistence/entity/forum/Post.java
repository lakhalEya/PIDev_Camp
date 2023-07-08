package tn.camps.tuncamps.persistence.entity.forum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.camps.tuncamps.persistence.entity.enumeration.CommunityCategory;
import tn.camps.tuncamps.persistence.entity.enumeration.Visibility;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPost;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private CommunityCategory category;
    @Temporal(TemporalType.DATE)
    private Date datePublication;
    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @OneToMany( mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "community_space_id")
    private CommunitySpace communitySpace;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;


}
