package tn.camps.tuncamps.persistence.entity.commun;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String country;
    private String city;
    private String postalCode;

    public City(String city, String country, String postalCode) {
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }
}
