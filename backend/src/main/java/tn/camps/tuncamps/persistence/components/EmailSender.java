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
    public void sendEmail(SimpleMailMessage email /*,String reservationDetails*/) {

        mailSender.send(email);


    }
}
