package tn.camps.tuncamps.persistence.components;

import lombok.*;
import tn.camps.tuncamps.persistence.entity.booking.Reservation;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="confirmationToken")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = Reservation.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "reservation")
    private Reservation reservation;

    public ConfirmationToken(Reservation reservation) {
        this.reservation = reservation;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}
