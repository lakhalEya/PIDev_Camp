package tn.camps.tuncamps.persistence.entity.parc;

import lombok.*;
import tn.camps.tuncamps.persistence.entity.commun.Location;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Parc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idParc;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ParcCategory category;

    @Embedded
    private Location location;
}
