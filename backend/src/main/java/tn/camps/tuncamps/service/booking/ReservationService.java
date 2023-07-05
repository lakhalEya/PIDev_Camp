package tn.camps.tuncamps.service.booking;


import tn.camps.tuncamps.persistence.entity.booking.Reservation;
import tn.camps.tuncamps.persistence.entity.booking.ReservationStatus;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.persistence.entity.user.User;


import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    Reservation findById(int id);
    List<Reservation> findAll();
    Reservation createReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    void deleteReservation(int id);
    List<Reservation> findResStatus(ReservationStatus status);
    List<Reservation> findResUser(User user);
    List<Reservation> findResParc(Parc parc);
    List<Reservation> findResSaleDate(LocalDate dateS);
    Reservation confirmReservation(String confirmationToken);

    List<Reservation> findResParcname(String parcname);

    List<Reservation> findResSale();


}
