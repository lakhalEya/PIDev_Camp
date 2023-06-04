package tn.camps.tuncamps.persistence.entities.commun;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Tariff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private String name;
    private String description;
    private int duration;
    @OneToOne
    private Currency currency;
}
