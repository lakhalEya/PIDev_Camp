package tn.camps.tuncamps.persistence.entity.commun;

import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Currency;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price;

    private String name;

    private String description;

    private int duration;

    private double discount;

    private Currency currency;

    private Date validityStartDate;

    private Date validityEndDate;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> applicableDays;

    private LocalTime applicableStartTime;

    private LocalTime applicableEndTime;

    private int minimumParticipants;

    private int maximumParticipants;

    @ElementCollection
    private Set<String> additionalServices;

    private String restrictions;
}