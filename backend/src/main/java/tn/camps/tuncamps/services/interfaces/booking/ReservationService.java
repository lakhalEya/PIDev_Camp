package tn.camps.tuncamps.services.interfaces.booking;


import tn.camps.tuncamps.persistence.entities.booking.Reservation;
import tn.camps.tuncamps.persistence.entities.parc.Parc;
import tn.camps.tuncamps.persistence.entities.user.User;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationService {
    Reservation findById(int id);
    List<Reservation> findAll();
    Reservation createReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    void deleteReservation(int id);
    List<Reservation> findResStatus(String status);
    List<Reservation> findResUser(User user);
    List<Reservation> findResParc(Parc parc);
    List<Reservation> findResSaleDate(LocalDate dateS);

}
