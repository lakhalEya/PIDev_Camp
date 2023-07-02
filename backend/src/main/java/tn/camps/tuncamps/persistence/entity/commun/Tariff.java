package tn.camps.tuncamps.persistence.entity.commun;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Tariff  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private String name;
    private String description;
    private int duration;
    //@Embedded
    //private Currency currency;

}
