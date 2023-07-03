package tn.camps.tuncamps.camperVirtualSpace;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.beans.Visibility;

@Getter
@Setter
@Entity
@Table(name = "camper_virtual_spaces")
public class CamperVirtualSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSpace;

    private String description;

/*    @Enumerated(EnumType.STRING)
    private Visibility contentVisibility;*/


}
