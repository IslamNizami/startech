package finalproject.startech.controllers;


import finalproject.startech.dtos.roledtos.RoleDto;
import finalproject.startech.dtos.userdtos.UserAddRoleDto;
import finalproject.startech.dtos.userdtos.UserDashboardListDto;
import finalproject.startech.dtos.userdtos.UserDto;
import finalproject.startech.models.Role;
import finalproject.startech.services.RoleService;
import finalproject.startech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @GetMapping("/admin/users")
    public String getUsers(Model model)
    {
        List<UserDashboardListDto> users = userService.getDashboardUsers();
        model.addAttribute("users", users);
        return "/dashboard/auth/user-list";

    }

    @GetMapping("/admin/users/role/{id}")
    public String addRole(@PathVariable Long id,Model model)
    {
        List<RoleDto> roles = roleService.getRoles();
        model.addAttribute("roles",roles);
        UserDto user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/dashboard/auth/user-role";
    }

    @PostMapping("/admin/users/addrole")
    public String addRole(UserAddRoleDto addRoleDto)
    {
        userService.addRole(addRoleDto);
        return "redirect:/admin/users";
    }


    @GetMapping("/admin/users/remove/{id}")
    public String removeUser(@ModelAttribute @PathVariable Long id) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }

}
