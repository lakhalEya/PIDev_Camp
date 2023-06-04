package tn.camps.tuncamps.services.interfaces.booking;


import tn.camps.tuncamps.persistence.entities.booking.Reservation;


import java.util.List;

public interface ReservationService {
    Reservation findById(int id);
    List<Reservation> findAll();
    Reservation createReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    void deleteReservation(int id);
}
