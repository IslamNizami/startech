package finalproject.startech.controllers;


import finalproject.startech.dtos.authdtos.RegisterDto;
import finalproject.startech.repositories.UserRepository;
import finalproject.startech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @PostMapping("/register")
    public String register(RegisterDto registerDto)
    {
        boolean res = userService.register(registerDto);
        if (res == false)
        {
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/auth/confirm")
    public String confirm(String email, String token)
    {
        userService.confirmEmail(email,token);
        return "redirect:/login";
    }


}
