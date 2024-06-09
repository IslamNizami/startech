package finalproject.startech.controllers;


import finalproject.startech.repositories.PackageRepository;
import finalproject.startech.services.PackageService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping("/admin/package")
    public String index()
    {
        return "/dashboard/package";
    }


    @GetMapping("/admin/package/create")
    public String createPackage(Model model)
    {
        return "/dashboard/package/create";
    }
}
