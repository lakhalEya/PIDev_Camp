package tn.camps.tuncamps.persistence.entity.forum;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class React {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReact;
    private String title;
    private String description;
//    @ManyToOne
//    private User user;
    @OneToOne(mappedBy="react")
    private Message message;
}
