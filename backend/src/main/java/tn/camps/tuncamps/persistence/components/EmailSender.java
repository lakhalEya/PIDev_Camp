package tn.camps.tuncamps.persistence.components;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    private final JavaMailSender mailSender;

    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void sendConfirmationEmail(String recipientEmail /*,String reservationDetails*/) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Reservation Confirmation");
        message.setText("Thank you for your reservation!\n\n Please confirm in those 24h "/*+ reservationDetails*/);
        mailSender.send(message);
    }
}
