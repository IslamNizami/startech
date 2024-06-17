package finalproject.startech.controllers;


import finalproject.startech.dtos.categorydtos.CategoryCreateDto;
import finalproject.startech.dtos.categorydtos.CategoryDto;
import finalproject.startech.dtos.roledtos.RoleCreateDto;
import finalproject.startech.dtos.roledtos.RoleDto;
import finalproject.startech.services.CategoryService;
import finalproject.startech.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/admin/role")
    public String role(Model model)
    {
        List<RoleDto> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        return "/dashboard/auth/role";
    }

    @GetMapping("/admin/role/create")
    public String createRole()
    {
        return "/dashboard/auth/role-create";
    }

    @PostMapping("/admin/role/create")
    public String createRole(@ModelAttribute RoleCreateDto roleCreateDto)
    {
        roleService.createRole(roleCreateDto);
        return "redirect:/admin/role";
    }
}
