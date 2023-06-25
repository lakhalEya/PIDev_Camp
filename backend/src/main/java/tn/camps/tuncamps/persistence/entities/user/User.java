package tn.camps.tuncamps.persistence.entities.user;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

}
