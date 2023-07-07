package tn.camps.tuncamps.persistence.entity.parc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import tn.camps.tuncamps.persistence.entity.booking.Reservation;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.entity.commun.Tariff;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Activity {
    public enum ActivityCategory {
        SPORTS,
        MUSIC,
        ARTS,
        OUTDOORS,
        OTHER
    }    public enum ActivityStatus {
        UPCOMING,
        ONGOING,
        COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne
    private Location location;

    private Date startDate;

    private Date endDate;

    private String organizer;

    @OneToOne
    private Tariff tariff;

    @ManyToOne
    private Parc parc;

    @Enumerated(EnumType.STRING)
    private ActivityCategory category;

    private Date creationDate;

    private Date lastModificationDate;

    private String description;

    private int maxParticipants;

    private boolean registrationRequired;

    @ElementCollection
    private List<String> tags;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private ActivityStatus status;


}