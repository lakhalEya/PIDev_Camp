package tn.camps.tuncamps.persistence.entity.booking;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import tn.camps.tuncamps.persistence.entity.commun.Tariff;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.persistence.entity.user.User;
import java.io.Serializable;
import java.time.LocalDate;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    //nombre de personne dans une seule reservation effectuée par un utilisateur
    //le prix va etre personnbr*tarif.price
    private int personnbr;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @OneToOne
    @JoinColumn(name = "tarif_id")
    private Tariff tarif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parc_id")
    private Parc parc;





}
