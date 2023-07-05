package tn.camps.tuncamps.persistence.entity.commun;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private boolean disponibilite;
    private double latitude;
    private double longitude;

    @ManyToOne
    private City city;
}

