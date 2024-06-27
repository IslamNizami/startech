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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class FeedbackController {

    @Autowired
    private UserService userService;
    @Autowired
    private FeedbackService feedbackService;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";


    @GetMapping("/admin/feedback/create")
    public String createFeedback(Model model)
    {
        List<UserFeedbackDto> users = userService.getAllFeedbackUsers();
        model.addAttribute("users", users);
        return "/dashboard/feedback-create";
    }

    @PostMapping("/admin/feedback/create")
    public String createFeedback(@ModelAttribute FeedbackCreateDto feedbackCreateDto,@RequestParam("image") MultipartFile image) throws IOException
    {
        UUID rand = UUID.randomUUID();
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, rand + image.getOriginalFilename());
        fileNames.append(image.getOriginalFilename());
        Files.write(fileNameAndPath, image.getBytes());
        feedbackCreateDto.setPhotoUrl(rand + image.getOriginalFilename());
        feedbackService.addFeedback(feedbackCreateDto);
        return "redirect:/admin";
    }
}
