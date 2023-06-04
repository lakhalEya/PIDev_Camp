package tn.camps.tuncamps.persistence.repositories.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.camps.tuncamps.persistence.entities.booking.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
