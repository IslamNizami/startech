package finalproject.startech.controllers;


import finalproject.startech.dtos.offerdtos.OfferCreateDto;
import finalproject.startech.dtos.offerdtos.OfferDto;
import finalproject.startech.dtos.tagdtos.TagCreateDto;
import finalproject.startech.dtos.tagdtos.TagDto;
import finalproject.startech.models.Offer;
import finalproject.startech.services.OfferService;
import finalproject.startech.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OfferController {
    @Autowired
    private OfferService offerService;

    @GetMapping("/admin/offer")
    public String offer(Model model)
    {
        List<OfferDto> offers = offerService.getAllOffers();
        model.addAttribute("offers",offers);
        return "dashboard/offer";
    }

    @GetMapping("/admin/offer/create")
    public String createTag()
    {
        return "/dashboard/offer-create";
    }

    @PostMapping("/admin/offer/create")
    public String createTag(@ModelAttribute OfferCreateDto offerCreateDto)
    {
        offerService.createOffer(offerCreateDto);
        return "redirect:/admin/offer";
    }
}
