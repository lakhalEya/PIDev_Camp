package tn.camps.tuncamps.persistence.entity.forum;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class React {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReact;
    private String title;
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Comment> comments;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Message> messages;
}
