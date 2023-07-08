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
import java.time.temporal.ChronoUnit;
import java.util.List;



//@CrossOrigin(origins = "*")
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

    @GetMapping("/allByUser/{id_user}")
    public ResponseEntity<List<Reservation>> getUserReservations(@PathVariable int id_user) {
        User user = userRepository.findById(id_user).orElse(null);
        //User user = userRepository.findById(1).orElse(null);
        List<Reservation> reservations = reservationService.findResUser(user);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/allWithSale")
    public ResponseEntity<List<Reservation>> getAllReservationsWithSale() {
        List<Reservation> reservations = reservationService.findResSale();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/resBySaleDate/{date}")
    public ResponseEntity<List<Reservation>> getReservationsBySaleDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
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

    @GetMapping("/nbReservation")
    public ResponseEntity<Integer> getNbReservations() {
        int nbReservation= reservationService.nbReservation();
        return ResponseEntity.status(HttpStatus.CREATED).body(nbReservation);
    }
    @GetMapping("/nbReservationParc")
    public ResponseEntity<Integer> getNbReservationsParc() {
        int nbReservation= reservationService.nbReservationParc();
        return ResponseEntity.status(HttpStatus.CREATED).body(nbReservation);
    }
    @GetMapping("/nbReservationActivity")
    public ResponseEntity<Integer> getNbReservationsActivity() {
        int nbReservation= reservationService.nbReservationActivity();
        return ResponseEntity.status(HttpStatus.CREATED).body(nbReservation);
    }



    @PostMapping("/addForActivity/{id_act}")
    public ResponseEntity<String> createActivityReservation(@RequestBody Reservation reservation, @PathVariable int id_act) {
        //User user = userRepository.findById(reservation.getUser().getId()).orElse(null);
        User user = userRepository.findById(1).orElse(null);
        Activity activity = activityRepository.findById(id_act).orElse(null);
        //à modifier Connected user
        reservation.setUser(user);
        if (activity == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Activity required");
        } else if (activity.getMaxParticipants()>=reservationService.personNumberByActivity(id_act) + reservation.getPersonnbr()) {
            if (activity.isRegistrationRequired()) {
                reservation.setActivity(activity);
                reservation.setCategory(ReservationCategory.ACTIVITY);
                reservationService.createReservation(reservation);
                return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Parc Required\"}");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\": \"Parc Reservation\"}");
            }
        }
            else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\": \"This parc is full!\"}");
            }
    }



    @PostMapping("/addForParc/{id_parc}")
    public ResponseEntity<Object> createParcReservation(@RequestBody Reservation reservation, @PathVariable int id_parc) {
        //User user = userRepository.findById(reservation.getUser().getId()).orElse(null);
        User user = userRepository.findById(1).orElse(null);
        Parc parc = parcRepository.findById(id_parc).orElse(null);
        //à modifier Connected user
        reservation.setUser(user);
        if (parc == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\": \"Parc Required\"}");
        } else {
            if (parc.getMaxCapacity() >= reservationService.personNumberByParc(id_parc) + reservation.getPersonnbr()) {
                reservation.setParc(parc);
                reservation.setCategory(ReservationCategory.PARC);
                reservationService.createReservation(reservation);
                return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Parc Reservation\"}");
            }
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\": \"This parc is full!\"}");
            }
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



    @PutMapping("/cancel/{id}")
    public ResponseEntity<Reservation> CancelReservation(@PathVariable int id) {
        Reservation reservation = reservationService.findById(id);
        LocalDate sysdate=LocalDate.now();
        long numberOfDays = ChronoUnit.DAYS.between(sysdate, reservation.getStartDate());

        try {
            if(reservation.getStatus()==ReservationStatus.CONFIRMED && numberOfDays>2){
                reservation.setStatus(ReservationStatus.CANCELED);
                reservationService.updateReservation(reservation);
                return ResponseEntity.ok(reservation);
            }
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
