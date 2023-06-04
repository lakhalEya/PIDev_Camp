package tn.camps.tuncamps.persistence.entities.booking;

import javax.persistence.*;
import lombok.*;
import tn.camps.tuncamps.persistence.entities.commun.Tariff;


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
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @OneToOne
    @JoinColumn(name = "tarif_id")
    private Tariff tarif;
    @ManyToMany(mappedBy = "reservations")
    private List<Sale> sales;


}
