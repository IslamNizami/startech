package finalproject.startech.controllers;


import finalproject.startech.dtos.blogdtos.BlogCreateDto;
import finalproject.startech.dtos.blogdtos.BlogDetailDto;
import finalproject.startech.dtos.blogdtos.BlogDto;
import finalproject.startech.dtos.blogdtos.BlogUpdateDto;
import finalproject.startech.dtos.categorydtos.CategoryDto;
import finalproject.startech.dtos.tagdtos.TagDto;
import finalproject.startech.services.BlogService;
import finalproject.startech.services.CategoryService;
import finalproject.startech.services.TagService;
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
public class BlogController {


    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";


    @GetMapping("/admin/blog")
    public String blog(Model model) {
        List<BlogDto> blogs = blogService.getBlogs();
        List<TagDto> tags = tagService.getAllTags();
        model.addAttribute("blogs", blogs);
        model.addAttribute("tags", tags);
        return "/dashboard/blog";
    }

    @GetMapping("/admin/blog/create")
    public String createBlog(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        List<TagDto> tags = tagService.getAllTags();
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tags);
        return "/dashboard/blog-create";
    }

    @PostMapping("/admin/blog/create")
    public String articleCreate(@ModelAttribute BlogCreateDto blogCreateDto, @RequestParam("image") MultipartFile image) throws IOException {
        {
            UUID rand = UUID.randomUUID();
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, rand + image.getOriginalFilename());
            fileNames.append(image.getOriginalFilename());
            Files.write(fileNameAndPath, image.getBytes());
            blogCreateDto.setPhotoUrl(rand + image.getOriginalFilename());
            blogService.createBlog(blogCreateDto);
            return "redirect:/admin/blog";
        }
    }

    @GetMapping("/admin/blog/remove/{id}")
    public String removeBlog(@ModelAttribute @PathVariable Long id) {
        blogService.removeBlog(id);
        return "redirect:/admin/blog";
    }

    @GetMapping("/admin/blog/update/{id}")
    public String updateBlog(@ModelAttribute @PathVariable Long id, Model model) {
        BlogUpdateDto blogUpdateDto = blogService.findUpdateBlog(id);
        List<CategoryDto> categories = categoryService.getAllCategories();
        List<TagDto> tags = tagService.getAllTags();
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tags);
        model.addAttribute("blog", blogUpdateDto);
        return "/dashboard/blog-update";
    }

    @PostMapping("/admin/blog/update")
    public String updateBlog(@ModelAttribute BlogUpdateDto blogUpdateDto,@RequestParam("image") MultipartFile image) throws IOException
    {
        UUID rand = UUID.randomUUID();
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, rand + image.getOriginalFilename());
        fileNames.append(image.getOriginalFilename());
        Files.write(fileNameAndPath, image.getBytes());
        blogUpdateDto.setPhotoUrl(rand + image.getOriginalFilename());
        blogService.updateBlog(blogUpdateDto);
        return "redirect:/admin/blog";
    }

    @GetMapping("detail/{id}/{seoUrl}")
    public  String detail(@PathVariable Long id, Model model)
    {
        BlogDetailDto blogDetailDto = blogService.blogDetail(id);
        model.addAttribute("blog",blogDetailDto);
        return "blog-detail";
    }
}