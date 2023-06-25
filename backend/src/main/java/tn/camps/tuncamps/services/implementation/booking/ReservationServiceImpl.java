package tn.camps.tuncamps.services.implementation.booking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.entities.booking.Reservation;
import tn.camps.tuncamps.persistence.entities.parc.Parc;
import tn.camps.tuncamps.persistence.entities.user.User;
import tn.camps.tuncamps.persistence.repositories.booking.ReservationRepository;
import tn.camps.tuncamps.services.interfaces.booking.ReservationService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Slf4j
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
    public Reservation createReservation(Reservation reservation) {
       System.out.println(reservation.getUser().getId());
        return reservationRepository.save(reservation);
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

    @Override
    public List<Reservation> findResStatus(String status) {
        return reservationRepository.findByStatus(status);
    }

    @Override
    public List<Reservation> findResUser(User user) {
        return reservationRepository.findByUser(user);
    }

    @Override
    public List<Reservation> findResParc(Parc parc) {
        return reservationRepository.findByParc(parc);
    }

    @Override
    public List<Reservation> findResSaleDate(LocalDate dateI) {
        return reservationRepository.findBySaleDate(dateI);
    }


}
