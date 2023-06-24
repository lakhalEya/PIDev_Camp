package tn.camps.tuncamps.persistence.entity.forum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tn.camps.tuncamps.persistence.entity.enumeration.Visibility;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "community_space _id")
    private CommunitySpace communitySpace;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Comment> comments;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "posts")
    private Set<Review> reviews;
}
