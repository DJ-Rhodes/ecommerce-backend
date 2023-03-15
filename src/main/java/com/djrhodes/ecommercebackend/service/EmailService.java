package com.djrhodes.ecommercebackend.service;

import com.djrhodes.ecommercebackend.exception.EmailFailureException;
import com.djrhodes.ecommercebackend.model.VerificationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service for handling emails being sent.
 */
@Service
public class EmailService {

    /** The from address to use on emails. */
    @Value("${email.address.from}")
    private String fromEmailAddress;
    /** The url of the front end for links. */
    @Value("${app.frontend.url}")
    private String url;
    /** The JavaMailSender instance. */
    private JavaMailSender javaMailSender;

    /**
     * Constructor for spring injection.
     * @param javaMailSender The JavaMailSender.
     */
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Makes a SimpleMailMessage for sending.
     * @return The SimpleMailMessage created.
     */
    private SimpleMailMessage makeMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmailAddress);
        return simpleMailMessage;
    }

    /**
     * Sends a verification email to the user.
     * @param verificationToken The verification token to be sent.
     * @throws EmailFailureException Thrown if are unable to send the email.
     */
    public void sendVerificationEmail(VerificationToken verificationToken) throws EmailFailureException {
        SimpleMailMessage simpleMailMessage = makeMailMessage();
        simpleMailMessage.setTo(verificationToken.getUser().getEmail());
        simpleMailMessage.setSubject("Verify Email Address");
        simpleMailMessage.setText("Please follow the verification link below to activate your account.\n" +
                url + "auth/verify?token=" + verificationToken.getToken());
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException mailException) {
            throw new EmailFailureException();
        }
    }
}
