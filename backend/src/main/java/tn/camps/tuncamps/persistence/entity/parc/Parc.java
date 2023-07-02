package tn.camps.tuncamps.persistence.entity.parc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import tn.camps.tuncamps.persistence.entity.booking.Reservation;
import tn.camps.tuncamps.persistence.entity.commun.Location;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@JsonIgnoreProperties("reservations")
public class Parc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idParc;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ParcCategory category;

    @OneToOne
    private Location location;
    @OneToMany(mappedBy = "parc")
    private List<Reservation> reservations;
}
