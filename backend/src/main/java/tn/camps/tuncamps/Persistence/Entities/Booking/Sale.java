package tn.camps.tuncamps.Persistence.Entities.Booking;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private Date startDate;
    private Date expirationDate;
    private double amount;
    @ManyToMany
    private List<Reservation> reservations;
}
