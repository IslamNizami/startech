package finalproject.startech.controllers;


import finalproject.startech.dtos.blogdtos.BlogDto;
import finalproject.startech.dtos.blogdtos.BlogHomeDto;
import finalproject.startech.dtos.blogdtos.BlogRecentDto;
import finalproject.startech.dtos.categorydtos.CategoryDto;
import finalproject.startech.dtos.feedbackdtos.FeedbackDto;
import finalproject.startech.dtos.packagedtos.PackageDto;
import finalproject.startech.dtos.packagedtos.PackageHomeDto;
import finalproject.startech.dtos.servicedtos.ServiceDto;
import finalproject.startech.dtos.servicedtos.ServiceHomeDto;
import finalproject.startech.dtos.tagdtos.TagDto;
import finalproject.startech.dtos.userdtos.MemberDto;
import finalproject.startech.dtos.userdtos.UserAddRoleDto;
import finalproject.startech.models.ContactForm;
import finalproject.startech.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private PackageService packageService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private UserService userService;
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/")
    public String index(Model model)
    {
        List<MemberDto> members = userService.getAllMembers();
        model.addAttribute("members", members);
        List<ServiceDto> services = serviceService.getAllServices();
        model.addAttribute("services", services);
        List<FeedbackDto> feedbacks = feedbackService.getAllFeedbacks();
        model.addAttribute("feedbacks", feedbacks);
        List<PackageDto> packages = packageService.getAllPackages();
        model.addAttribute("packages", packages);
        model.addAttribute("contactForm", new ContactForm());
        List<BlogHomeDto> latestBlogs = blogService.getLatestBlogs();
        model.addAttribute("blogs", latestBlogs);
        return "home";
    }
    @GetMapping("/about")
    public String about(Model model)
    {
        List<MemberDto> members = userService.getAllMembers();
        model.addAttribute("members", members);
        return "about";
    }
    @GetMapping("/services")
    public String services(Model model)
    {
        List<ServiceDto> services = serviceService.getAllServices();
        model.addAttribute("services", services);
        List<FeedbackDto> feedbacks = feedbackService.getAllFeedbacks();
        model.addAttribute("feedbacks", feedbacks);
        return "services";
    }
    @GetMapping("/blog")
    public String blog(Model model)
    {
        List<BlogHomeDto> blogs = blogService.getHomeBlogs();
        List<CategoryDto> categories = categoryService.getAllCategories();
        List<TagDto> tags = tagService.getAllTags();
        List<BlogRecentDto> latestBlogs = blogService.getLatest5Blogs();
        model.addAttribute("blogs", blogs);
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tags);
        model.addAttribute("latestBlogs", latestBlogs);
        return "blog";
    }
    @GetMapping("/features")
    public String features()
    {
        return "feature";
    }
    @GetMapping("/price")
    public String price(Model model)
    {
        List<PackageDto> packages = packageService.getAllPackages();
        model.addAttribute("packages", packages);
        model.addAttribute("contactForm", new ContactForm());
        return "price";
    }
    @GetMapping("/team")
    public String team(Model model)
    {
        List<MemberDto> members = userService.getAllMembers();
        model.addAttribute("members", members);
        return "team";
    }
    @GetMapping("/feedbacks")
    public String feedbacks(Model model)
    {
        List<FeedbackDto> feedbacks = feedbackService.getAllFeedbacks();
        model.addAttribute("feedbacks", feedbacks);
        return "feedback";
    }

}
