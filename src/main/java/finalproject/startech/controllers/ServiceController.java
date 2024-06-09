package finalproject.startech.controllers;


import finalproject.startech.dtos.categorydtos.CategoryDto;
import finalproject.startech.dtos.servicedtos.ServiceCreateDto;
import finalproject.startech.dtos.servicedtos.ServiceDto;
import finalproject.startech.dtos.servicedtos.ServiceUpdateDto;
import finalproject.startech.services.CategoryService;
import finalproject.startech.services.ServiceService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class ServiceController {

    @Autowired
    private ServiceService serviceService;
    @Autowired
    private CategoryService categoryService;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

    @GetMapping("/admin/service")
    public String service(Model model)
    {
        List<ServiceDto> services = serviceService.getAllServices();
        model.addAttribute("services", services);
        return "/dashboard/service";
    }

    @GetMapping("/admin/service/create")
    public String createService(Model model)
    {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "/dashboard/service-create";
    }

    @PostMapping("/admin/service/create")
    public String createService(@ModelAttribute ServiceCreateDto serviceCreateDto, @RequestParam("image") MultipartFile image) throws IOException
    {
        UUID rand = UUID.randomUUID();
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY,rand+image.getOriginalFilename());
        fileNames.append(image.getOriginalFilename());
        Files.write(fileNameAndPath,image.getBytes());

        serviceCreateDto.setIcon(rand+image.getOriginalFilename());
        serviceService.addService(serviceCreateDto);
        return "redirect:/admin/service";
    }


    @GetMapping("/admin/service/remove/{id}")
    public String removeService(@PathVariable Long id)
    {
        serviceService.removeService(id);
        return "redirect:/admin/service";
    }

    @GetMapping("/admin/service/update/{id}")
    public String updateService(@ModelAttribute @PathVariable Long id, Model model)
    {
        ServiceUpdateDto serviceUpdateDto = serviceService.findUpdateService(id);
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("service", serviceUpdateDto);
        return "/dashboard/service-update";
    }

    @PostMapping("/admin/service/update")
    public String updateService(@ModelAttribute ServiceUpdateDto serviceUpdateDto,@RequestParam("image") MultipartFile image) throws IOException
    {
        UUID rand = UUID.randomUUID();
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY,rand+image.getOriginalFilename());
        fileNames.append(image.getOriginalFilename());
        Files.write(fileNameAndPath,image.getBytes());

        serviceUpdateDto.setIcon(rand+image.getOriginalFilename());
        serviceService.updateService(serviceUpdateDto);
        return "redirect:/admin/service";
    }
}
