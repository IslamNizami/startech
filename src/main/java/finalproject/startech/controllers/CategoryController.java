package finalproject.startech.controllers;


import finalproject.startech.dtos.categorydtos.CategoryCreateDto;
import finalproject.startech.dtos.categorydtos.CategoryDto;
import finalproject.startech.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/category")
    public String category(Model model)
    {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "/dashboard/category";
    }

    @GetMapping("/admin/category/create")
    public String createCategory()
    {
        return "/dashboard/category-create";
    }

    @PostMapping("/admin/category/create")
    public String createCategory(@ModelAttribute CategoryCreateDto categoryCreateDto)
    {
        categoryService.createCategory(categoryCreateDto);
        return "redirect:/admin/category";
    }
}
