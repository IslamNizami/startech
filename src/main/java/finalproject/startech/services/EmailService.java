package finalproject.startech.services;

import finalproject.startech.models.ContactForm;
import finalproject.startech.models.UserEntity;
import org.springframework.mail.SimpleMailMessage;

import java.util.Locale;

public interface EmailService
{
    void sendConfirmationEmail(String email, String token);
    void sendEmail(ContactForm contactForm);
}
