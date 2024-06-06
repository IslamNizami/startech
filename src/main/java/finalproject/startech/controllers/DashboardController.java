package finalproject.startech.controllers;


import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/admin")
    public String index()
    {
        return "/dashboard/home";
    }

}
