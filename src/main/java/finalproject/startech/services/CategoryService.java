package finalproject.startech.services;

import finalproject.startech.dtos.categorydtos.CategoryCreateDto;
import finalproject.startech.dtos.categorydtos.CategoryDto;

import java.util.List;

public interface CategoryService {

     List<CategoryDto> getAllCategories();
     void createCategory(CategoryCreateDto categoryCreateDto);
}
