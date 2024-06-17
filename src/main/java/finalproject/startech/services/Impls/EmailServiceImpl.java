package finalproject.startech.services.Impls;

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
        message.setTo("vicenta50@ethereal.email");
        message.setSubject("Confirmation Email");
        String res = "http://localhost:6060/auth/confirm?email="+email+"&token="+token;
        message.setText(res);
        mailSender.send(message);
    }

}
