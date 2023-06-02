package tn.camps.tuncamps.TestControllers.Booking;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.camps.tuncamps.Controllers.Booking.ReservationController;
import tn.camps.tuncamps.Persistence.Entities.Booking.Reservation;
import tn.camps.tuncamps.Services.Interfaces.Booking.ReservationService;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationTestController {
    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @Test
    public void testGetReservationById_ReturnsReservation() {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        when(reservationService.findById(1)).thenReturn(reservation);

        ResponseEntity<Reservation> response = reservationController.getReservationById(1);

        verify(reservationService, times(1)).findById(1);
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame(reservation, response.getBody());
    }

    @Test
    public void testGetReservationById_ReservationNotFound_ReturnsNotFoundStatus() {
        when(reservationService.findById(1)).thenThrow(new RuntimeException("Reservation not found"));

        ResponseEntity<Reservation> response = reservationController.getReservationById(1);

        verify(reservationService, times(1)).findById(1);
        assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllReservations_ReturnsReservationsList() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        when(reservationService.findAll()).thenReturn(reservations);

        ResponseEntity<List<Reservation>> response = reservationController.getAllReservations();

        verify(reservationService, times(1)).findAll();
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame(reservations, response.getBody());
    }

    @Test
    public void testCreateReservation_ReturnsCreatedStatusAndReservation() {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        when(reservationService.createReservation(any(Reservation.class))).thenReturn(reservation);

        ResponseEntity<Reservation> response = reservationController.createReservation(new Reservation());

        verify(reservationService, times(1)).createReservation(any(Reservation.class));
        assertSame(HttpStatus.CREATED, response.getStatusCode());
        assertSame(reservation, response.getBody());
    }

    @Test
    public void testUpdateReservation_ReturnsUpdatedReservation() {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        when(reservationService.updateReservation(any(Reservation.class))).thenReturn(reservation);

        ResponseEntity<Reservation> response = reservationController.updateReservation(1, new Reservation());

        verify(reservationService, times(1)).updateReservation(any(Reservation.class));
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame(reservation, response.getBody());
    }

    @Test
    public void testUpdateReservation_ReservationNotFound_ReturnsNotFoundStatus() {
        when(reservationService.updateReservation(any(Reservation.class))).thenThrow(new RuntimeException("Reservation not found"));

        ResponseEntity<Reservation> response = reservationController.updateReservation(1, new Reservation());

        verify(reservationService, times(1)).updateReservation(any(Reservation.class));
        assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteReservation_ReturnsNoContentStatus() {
        ResponseEntity<Void> response = reservationController.deleteReservation(1);

        verify(reservationService, times(1)).deleteReservation(1);
        assertSame(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteReservation_ReservationNotFound_ReturnsNotFoundStatus() {
        doThrow(new RuntimeException("Reservation not found")).when(reservationService).deleteReservation(1);

        ResponseEntity<Void> response = reservationController.deleteReservation(1);

        verify(reservationService, times(1)).deleteReservation(1);
        assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
