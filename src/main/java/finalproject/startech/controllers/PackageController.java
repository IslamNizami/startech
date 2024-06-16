package finalproject.startech.controllers;


import finalproject.startech.dtos.offerdtos.OfferDto;
import finalproject.startech.dtos.packagedtos.PackageCreateDto;
import finalproject.startech.dtos.packagedtos.PackageDto;
import finalproject.startech.dtos.packagedtos.PackageUpdateDto;
import finalproject.startech.models.Offer;
import finalproject.startech.models.Package;
import finalproject.startech.repositories.PackageRepository;
import finalproject.startech.services.OfferService;
import finalproject.startech.services.PackageService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PackageController {

    @Autowired
    private PackageService packageService;
    @Autowired
    private OfferService offerService;

    @GetMapping("/admin/package")
    public String index(Model model)
    {
        List<PackageDto> packages = packageService.getAllPackages();
        List<OfferDto> offers = offerService.getAllOffers();
        model.addAttribute("packages", packages);
        model.addAttribute("offers", offers);
        return "/dashboard/package";
    }


    @GetMapping("/admin/package/create")
    public String createPackage(Model model)
    {
        List<PackageDto> packages = packageService.getAllPackages();
        List<OfferDto> offers = offerService.getAllOffers();
        model.addAttribute("packages", packages);
        model.addAttribute("offers", offers);
        return "/dashboard/package-create";
    }

    @PostMapping("/admin/package/create")
    public  String createPackage(@ModelAttribute PackageCreateDto packageCreateDto)
    {
        packageService.createPackage(packageCreateDto);
        return "redirect:/admin/package";
    }

    @GetMapping("/admin/package/remove/{id}")
    public String removePackage(@PathVariable Long id)
    {
        packageService.removePackage(id);
        return "redirect:/admin/package";
    }

    @GetMapping("/admin/package/update/{id}")
    public String updatePackage(@PathVariable Long id, Model model)
    {
        PackageUpdateDto paketUpdateDto = packageService.findUpdatePackage(id);
        List<OfferDto> offers = offerService.getAllOffers();
        model.addAttribute("offers", offers);
        model.addAttribute("paket", paketUpdateDto);
        return "/dashboard/package-update";
    }

    @PostMapping("/admin/package/update")
    public String updatePackage(@ModelAttribute PackageUpdateDto packageUpdateDto)
    {
        packageService.updatePackage(packageUpdateDto);
        return "redirect:/admin/package";
    }

}
