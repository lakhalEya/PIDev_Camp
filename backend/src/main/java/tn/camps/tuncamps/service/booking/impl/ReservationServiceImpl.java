package tn.camps.tuncamps.service.booking.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import tn.camps.tuncamps.persistence.components.ConfirmationToken;
import tn.camps.tuncamps.persistence.components.EmailSender;
import tn.camps.tuncamps.persistence.entity.booking.Reservation;
import tn.camps.tuncamps.persistence.entity.booking.ReservationStatus;
import tn.camps.tuncamps.persistence.entity.parc.Parc;
import tn.camps.tuncamps.persistence.entity.user.User;
import tn.camps.tuncamps.persistence.repository.booking.ConfirmationRepository;
import tn.camps.tuncamps.persistence.repository.booking.ReservationRepository;
import tn.camps.tuncamps.service.booking.ReservationService;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ConfirmationRepository confirmationTokenRepository;

    private final EmailSender emailSender;

    public ReservationServiceImpl(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

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
        //ajouter les controles comme nbrpersonnes limites etc
        reservationRepository.save(reservation);
        ConfirmationToken confirmationToken = new ConfirmationToken(reservation);
        confirmationTokenRepository.save(confirmationToken);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("nefzimaysa27@gmail.Com");//a remplacer par reservation.user=>finduser.getEmail
        message.setSubject("Reservation Confirmation");
        message.setText("Thank you for your reservation!\n\n " +
                "To confirm please click here in less then 5 minutes: \n\n " +
                "http://localhost:8082/tunCamp/reservations/confirm-reservation?token="+confirmationToken.getConfirmationToken());// "/*+ reservationDetails*/);
        emailSender.sendEmail(message);
        return reservation;
    }


    @Override
    public Reservation confirmReservation(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        Reservation reservation = token.getReservation();
        if(token != null)
        {
            reservation.setStatus(ReservationStatus.CONFIRMED);
            reservationRepository.save(reservation);
            return reservation;
        }
        return reservation;
    }

    @Override
    public List<Reservation> findResParcname(String parcname) {
        return reservationRepository.findByParcName(parcname);
    }

    @Override
    public List<Reservation> findResSale() {
        return  reservationRepository.findBySaleFound();
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
    public List<Reservation> findResStatus(ReservationStatus status) {
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
