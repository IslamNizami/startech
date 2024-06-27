package finalproject.startech.controllers;


import finalproject.startech.models.ContactForm;
import finalproject.startech.services.EmailService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailService emailService;

    @GetMapping("/contact")
    public String contact(Model model)
    {
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }

    @PostMapping("/contact")
    public String contact(@Valid @ModelAttribute("contactForm") ContactForm contactForm, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "contact";
        }
        emailService.sendEmail(contactForm);

        return "success-page";
    }
}
