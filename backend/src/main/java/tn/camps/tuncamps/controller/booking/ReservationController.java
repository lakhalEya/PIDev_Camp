package tn.camps.tuncamps.controller.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.camps.tuncamps.persistence.components.EmailSender;
import tn.camps.tuncamps.persistence.entity.booking.Reservation;
import tn.camps.tuncamps.persistence.entity.booking.ReservationCategory;
import tn.camps.tuncamps.persistence.entity.booking.ReservationStatus;
import tn.camps.tuncamps.persistence.entity.booking.Sale;

import tn.camps.tuncamps.persistence.entity.commun.Tariff;
import tn.camps.tuncamps.persistence.entity.parc.Activity;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.persistence.entity.user.User;
import tn.camps.tuncamps.persistence.repository.booking.ReservationRepository;
import tn.camps.tuncamps.persistence.repository.booking.SaleRepository;
import tn.camps.tuncamps.persistence.repository.commun.TariffRepository;
import tn.camps.tuncamps.persistence.repository.parc.ActivityRepository;
import tn.camps.tuncamps.persistence.repository.parc.ParcRepository;
import tn.camps.tuncamps.persistence.repository.user.UserRepository;
import tn.camps.tuncamps.service.booking.ReservationService;

import java.time.LocalDate;
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
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private TariffRepository tarifRepository;
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
    @GetMapping("/allWithSale")
    public ResponseEntity<List<Reservation>> getAllReservationsWithSale() {
        List<Reservation> reservations = reservationService.findResSale();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/resBySaleDate/{date}")
    public ResponseEntity<List<Reservation>> getReservationsBySaleDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate date) {
        List<Reservation> reservations = reservationService.findResSaleDate(date);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/resByStatus/{status}")
    public ResponseEntity<List<Reservation>> getReservationsByStatus(@PathVariable("status") ReservationStatus status) {
        List<Reservation> reservations = reservationService.findResStatus(status);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/resByParcName/{parcname}")
    public ResponseEntity<List<Reservation>> getReservationsByParcName(@PathVariable("parcname") String parcname) {
        List<Reservation> reservations = reservationService.findResParcname(parcname);
        return ResponseEntity.ok(reservations);
    }

    /*@GetMapping("/resByParc/{id}")
    public ResponseEntity<List<Reservation>> getReservationsByParc(@PathVariable int id) {
        List<Reservation> reservations = reservationService.findResParc(id);
        return ResponseEntity.ok(reservations);
    }*/

    @PostMapping("/addForActiviy")
    public ResponseEntity<String> createActivityReservation(@RequestBody Reservation reservation) {
        User user = userRepository.findById(reservation.getUser().getId()).orElse(null);
        Parc parc = parcRepository.findById(reservation.getParc().getIdParc()).orElse(null);
        Activity activity = activityRepository.findById(reservation.getParc().getIdParc()).orElse(null);
        //à modifier Connected user
        reservation.setUser(user);
        if (parc!=null || activity==null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This reservation is for activity only");
        }
        else {
            if (activity.isRegistrationRequired()){
                reservation.setActivity(activity);
                reservation.setCategory(ReservationCategory.ACTIVITY);
                reservationService.createReservation(reservation);
                return ResponseEntity.status(HttpStatus.CREATED).body("Activity Reservation");}
            else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This Activity does not need reservation ");
            }
        }

    }

    @PostMapping("/addForParc")
    public ResponseEntity<String> createParcReservation(@RequestBody Reservation reservation) {
        User user = userRepository.findById(reservation.getUser().getId()).orElse(null);
        Parc parc = parcRepository.findById(reservation.getParc().getIdParc()).orElse(null);
        Activity activity = activityRepository.findById(reservation.getParc().getIdParc()).orElse(null);
        //Tariff tarif = tarifRepository.findById(reservation.getTarif().getId()).orElse(null);
        //à modifier Connected user
        reservation.setUser(user);
        if (parc==null || activity!=null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This reservation is for Parc only");
        }
        else  {
            reservation.setParc(parc);
            reservation.setCategory(ReservationCategory.PARC);
            reservationService.createReservation(reservation);
            return ResponseEntity.status(HttpStatus.CREATED).body("Parc Reservation");
        }
    }


    @RequestMapping(value="/confirm-reservation", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Reservation> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        Reservation confirmedReservation = reservationService.confirmReservation(confirmationToken);
        return ResponseEntity.status(HttpStatus.OK).body(confirmedReservation);
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
