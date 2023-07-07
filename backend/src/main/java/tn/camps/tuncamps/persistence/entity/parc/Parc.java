package tn.camps.tuncamps.persistence.entity.parc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import tn.camps.tuncamps.persistence.entity.booking.Reservation;
import tn.camps.tuncamps.persistence.entity.commun.Location;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Parc {

    public enum ParcStatus {
        ENABLED,
        DISABLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idParc;

    private String name;

    private String description;

    private int maxCapacity;

    @Enumerated(EnumType.STRING)
    private ParcCategory category;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    @Enumerated(EnumType.STRING)
    private ParcStatus status;

    @OneToOne
    private Location location;

    private String imageURL;

    @ElementCollection
    private List<String> amenities;

    private double rating;

//    @OneToMany
//    private List<Review> reviews;

    //TODO : Change it with User
    private String owner;


}
