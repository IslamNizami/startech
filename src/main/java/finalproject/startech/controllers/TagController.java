package finalproject.startech.controllers;


import finalproject.startech.dtos.tagdtos.TagCreateDto;
import finalproject.startech.dtos.tagdtos.TagDto;
import finalproject.startech.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/admin/tag")
    public String tag(Model model)
    {
        List<TagDto> tags = tagService.getAllTags();
        model.addAttribute("tags",tags);
        return "dashboard/tag";
    }

    @GetMapping("/admin/tag/create")
    public String createTag()
    {
        return "/dashboard/tag-create";
    }

    @PostMapping("/admin/tag/create")
    public String createTag(@ModelAttribute TagCreateDto tagCreateDto)
    {
        tagService.createTag(tagCreateDto);
        return "redirect:/admin/tag";
    }
}
