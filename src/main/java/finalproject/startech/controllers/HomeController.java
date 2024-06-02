package finalproject.startech.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index()
    {
        return "home";
    }
    @GetMapping("/about")
    public String about()
    {
        return "about";
    }
    @GetMapping("/contact")
    public String contact()
    {
        return "contact";
    }
    @GetMapping("/services")
    public String services()
    {
        return "services";
    }
    @GetMapping("/blog")
    public String blog()
    {
        return "blog";
    }
    @GetMapping("/features")
    public String features()
    {
        return "feature";
    }
    @GetMapping("/price")
    public String price()
    {
        return "price";
    }
    @GetMapping("/team")
    public String team()
    {
        return "team";
    }
    @GetMapping("/feedbacks")
    public String feedbacks()
    {
        return "feedback";
    }
    @GetMapping("/quote")
    public String quote()
    {
        return "quote";
    }

}
