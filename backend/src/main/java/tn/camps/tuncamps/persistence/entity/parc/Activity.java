package tn.camps.tuncamps.persistence.entity.parc;

import lombok.*;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.entity.commun.Tariff;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity

public class Activity {
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

}