package finalproject.startech.services.Impls;

import finalproject.startech.models.ContactForm;
import finalproject.startech.models.UserEntity;
import finalproject.startech.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendConfirmationEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("islamronaldo046@gmail.com");
        message.setTo(email);
        message.setSubject("Confirmation Email");
        String res = "http://localhost:6060/auth/confirm?email="+email+"&token="+token;
        message.setText(res);
        mailSender.send(message);
    }

    @Override
    public void sendEmail(ContactForm contactForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("islamronaldo046@gmail.com");
        message.setSubject(contactForm.getSubject());
        message.setFrom(contactForm.getEmail());
        message.setText("Name: " + contactForm.getName() + "\n"
                + "Email: " + contactForm.getEmail() + "\n"
                + "Message: " + contactForm.getMessage());
        mailSender.send(message);
    }


}
