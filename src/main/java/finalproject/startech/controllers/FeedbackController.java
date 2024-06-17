package finalproject.startech.controllers;


import finalproject.startech.dtos.feedbackdtos.FeedbackCreateDto;
import finalproject.startech.dtos.userdtos.UserDto;
import finalproject.startech.dtos.userdtos.UserFeedbackDto;
import finalproject.startech.services.FeedbackService;
import finalproject.startech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private UserService userService;
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/admin/feedback/create")
    public String createFeedback(Model model)
    {
        List<UserFeedbackDto> users = userService.getAllFeedbackUsers();
        model.addAttribute("users", users);
        return "/dashboard/feedback-create";
    }

    @PostMapping("/admin/feedback/create")
    public String createFeedback(@ModelAttribute FeedbackCreateDto feedbackCreateDto)
    {
        feedbackService.addFeedback(feedbackCreateDto);
        return "redirect:/admin/tag";
    }
}
