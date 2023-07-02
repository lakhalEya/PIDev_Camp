package tn.camps.tuncamps.persistence.entity.forum;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.camps.tuncamps.persistence.entity.enumeration.Visibility;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    @Temporal(TemporalType.DATE)
    private Date datePublication;
    @Enumerated(EnumType.STRING)
    private Visibility visibility;
    @ManyToOne
    @JoinColumn(name = "community_space_id")
    private CommunitySpace communitySpace;
//    @ManyToOne
//    private User user;
}
