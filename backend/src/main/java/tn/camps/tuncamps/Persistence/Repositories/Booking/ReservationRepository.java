package tn.camps.tuncamps.Persistence.Repositories.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.camps.tuncamps.Persistence.Entities.Booking.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
