package tn.camps.tuncamps.Persistence.Entities.Booking;

import javax.persistence.*;
import lombok.*;
import tn.camps.tuncamps.Persistence.Entities.Commun.Tariff;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date startDate;
    private Date endDate;
    @OneToOne
    private Tariff tarif;
    @ManyToMany(mappedBy = "reservations")
    private List<Sale> sales;


}
