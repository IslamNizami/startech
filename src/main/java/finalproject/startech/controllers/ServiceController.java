package finalproject.startech.controllers;


import finalproject.startech.dtos.categorydtos.CategoryDto;
import finalproject.startech.dtos.servicedtos.ServiceCreateDto;
import finalproject.startech.services.CategoryService;
import finalproject.startech.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ServiceController {

    @Autowired
    private ServiceService serviceService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/service")
    public String service()
    {
        return "/dashboard/service";
    }

    @GetMapping("/admin/service/service-create")
    public String createService(Model model)
    {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "/dashboard/service-create";
    }

    @PostMapping("/admin/service/create")
    public String createService(@ModelAttribute ServiceCreateDto serviceCreateDto)
    {
        serviceService.addService(serviceCreateDto);
        return "redirect:/admin/service";
    }

}
