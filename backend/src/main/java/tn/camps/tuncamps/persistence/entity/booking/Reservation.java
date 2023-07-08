package tn.camps.tuncamps.persistence.entity.booking;

import javax.persistence.*;
import javax.swing.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import tn.camps.tuncamps.persistence.entity.commun.Tariff;
import tn.camps.tuncamps.persistence.entity.parc.Activity;
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
    private int personnbr;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Enumerated(EnumType.STRING)
    private ReservationCategory category;


    @ManyToOne(optional = true)
    @JoinColumn(name = "sale_id")
    private Sale sale;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = true)
    @JoinColumn(name = "parc_id")
    private Parc parc;

    @ManyToOne(optional = true)
    @JoinColumn(name = "activity_id")
    private Activity activity;





}
