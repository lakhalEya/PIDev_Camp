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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMessage;
    private String content;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "messages")
    private Set<React> reacts;

}
