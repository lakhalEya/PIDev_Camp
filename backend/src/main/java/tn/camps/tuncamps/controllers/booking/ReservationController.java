package tn.camps.tuncamps.controllers.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.camps.tuncamps.persistence.components.EmailSender;
import tn.camps.tuncamps.persistence.entities.booking.Reservation;
import tn.camps.tuncamps.persistence.entities.booking.Sale;
import tn.camps.tuncamps.persistence.entities.parc.Parc;
import tn.camps.tuncamps.persistence.entities.user.User;
import tn.camps.tuncamps.persistence.repositories.booking.ReservationRepository;
import tn.camps.tuncamps.persistence.repositories.booking.SaleRepository;
import tn.camps.tuncamps.persistence.repositories.parc.ParcRepository;
import tn.camps.tuncamps.persistence.repositories.user.UserRepository;
import tn.camps.tuncamps.services.interfaces.booking.ReservationService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParcRepository parcRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    private final EmailSender emailSender;

    public ReservationController(EmailSender emailSender) {
        this.emailSender = emailSender;
    }


    @GetMapping("/getOne/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable int id) {
        try {
            Reservation reservation = reservationService.findById(id);
            return ResponseEntity.ok(reservation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/resBySaleDate")
    public ResponseEntity<List<Reservation>> getReservationsBySaleDate(@RequestBody  LocalDate date) {
        List<Reservation> reservations = reservationService.findResSaleDate(date);
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/add")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {

        Sale sale = saleRepository.findById(reservation.getSale().getId()).orElse(null);
        User user = userRepository.findById(reservation.getUser().getId()).orElse(null);
        Parc parc = parcRepository.findById(reservation.getParc().getIdParc()).orElse(null);
        reservation.setSale(sale);
        //à modifier Connected user
        reservation.setUser(user);
        reservation.setParc(parc);
        Reservation createdReservation = reservationService.createReservation(reservation);
        //emailSender.sendConfirmationEmail("nefzimaysa27@gmail.com");
        //emailSender.sendConfirmationEmail("");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        reservation.setId(id);
        try {
            Reservation updatedReservation = reservationService.updateReservation(reservation);
            return ResponseEntity.ok(updatedReservation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PutMapping("/affectSale/{idR}/{idS}")
    public ResponseEntity<Reservation> affectSaleToReservation(@PathVariable int idR,@PathVariable int idS) {
        Reservation reservation = reservationRepository.findById(idR).orElse(null);
        Sale sale = saleRepository.findById(idS).orElse(null);
        reservation.setSale(sale);
        try {
            Reservation updatedReservation = reservationService.updateReservation(reservation);
            return ResponseEntity.ok(updatedReservation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
