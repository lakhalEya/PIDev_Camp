package tn.camps.tuncamps.Services.Interfaces.Booking;


import tn.camps.tuncamps.Persistence.Entities.Booking.Reservation;


import java.util.List;

public interface ReservationService {
    Reservation findById(int id);
    List<Reservation> findAll();
    Reservation createReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    void deleteReservation(int id);
}
