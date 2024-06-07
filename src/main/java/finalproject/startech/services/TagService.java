package finalproject.startech.services;

import finalproject.startech.dtos.categorydtos.CategoryCreateDto;
import finalproject.startech.dtos.categorydtos.CategoryDto;
import finalproject.startech.dtos.tagdtos.TagCreateDto;
import finalproject.startech.dtos.tagdtos.TagDto;

import java.util.List;

public interface TagService {

    List<TagDto> getAllTags();
    void createTag(TagCreateDto tagCreateDto);
}
