package tn.camps.tuncamps.persistence.entity.forum;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReview;
    private String title;
    private String Content;
    private int rating;
    @Temporal(TemporalType.DATE)
    private Date reviewDate;
    @ManyToOne
    Post posts;

}
