package tn.camps.tuncamps.persistence.entities.parc;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import tn.camps.tuncamps.persistence.entities.booking.Reservation;

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

    @OneToMany(mappedBy = "parc")
    private List<Reservation> reservations;


}