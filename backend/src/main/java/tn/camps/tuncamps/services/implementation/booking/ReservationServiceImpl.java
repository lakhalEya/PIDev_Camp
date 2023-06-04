package tn.camps.tuncamps.services.implementation.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entities.booking.Reservation;
import tn.camps.tuncamps.persistence.repositories.booking.ReservationRepository;
import tn.camps.tuncamps.services.interfaces.booking.ReservationService;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation findById(int id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation createReservation(Reservation Reservation) {
        return reservationRepository.save(Reservation);
    }

    @Override
    public Reservation updateReservation(Reservation Reservation) {
        if (!reservationRepository.existsById(Reservation.getId())) {
            throw new RuntimeException("Reservation not found with id: " + Reservation.getId());
        }
        return reservationRepository.save(Reservation);
    }

    @Override
    public void deleteReservation(int id) {
        if (!reservationRepository.existsById(id)) {
            throw new RuntimeException("Reservation not found with id: " + id);
        }
        reservationRepository.deleteById(id);
    }
}
