package finalproject.startech.controllers;


import finalproject.startech.dtos.userdtos.UserDto;
import finalproject.startech.models.PasswordResetToken;
import finalproject.startech.models.UserEntity;
import finalproject.startech.repositories.PasswordResetTokenRepository;
import finalproject.startech.repositories.UserRepository;
import finalproject.startech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PasswordController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @GetMapping("/forgotPassword")
    public String forgotPassword()
    {
        return "forgotPassword";
    }
    @GetMapping("/resetPassword")
    public String resetPassword()
    {
        return "newPassword";
    }

    @PostMapping("/forgotPassword")
    public String processForgotPassword(@ModelAttribute UserDto userDto)
    {
        UserEntity user = userRepository.findByEmail(userDto.getEmail());
        String output = "";
        if (user != null)
        {
             output = userService.sendPasswordResetEmail(user);
        }
        if (output.equals("success"))
        {
            return "redirect:/register?success";
        }
        return "redirect:/login?error";
    }

    @GetMapping("/resetPassword/{token}")
    public String resetPasswordForm(String token, Model model)
    {
        PasswordResetToken reset = passwordResetTokenRepository.findByToken(token);
        if (reset != null && userService.hasExpired(reset.getExpiryDate()))
        {
            model.addAttribute("email",reset.getUser().getEmail());
            return "newPassword";
        }
        return "redirect:/forgotPassword?error";
    }


    @PostMapping("/resetPassword/{token}")
    public String passwordResetProcess(@ModelAttribute UserDto userDto)
    {
        UserEntity user = userRepository.findByEmail(userDto.getEmail());
        if (user != null)
        {
            user.setPassword(userDto.getPassword());
            userRepository.save(user);
        }
        return "redirect:/login";
    }


}
