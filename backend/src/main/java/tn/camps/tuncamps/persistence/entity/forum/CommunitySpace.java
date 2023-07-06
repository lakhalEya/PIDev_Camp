package tn.camps.tuncamps.persistence.entity.forum;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.camps.tuncamps.persistence.entity.enumeration.CommunityCategory;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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
    @OneToMany(mappedBy = "communitySpace", cascade = CascadeType.ALL)
    private Set<Post> posts;
    @Lob
    private byte[] fileData;
    private String fileName;
    private String fileType;
//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<User> Users;
}
