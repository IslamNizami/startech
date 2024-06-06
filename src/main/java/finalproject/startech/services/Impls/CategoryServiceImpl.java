package finalproject.startech.services.Impls;

import finalproject.startech.dtos.categorydtos.CategoryCreateDto;
import finalproject.startech.dtos.categorydtos.CategoryDto;
import finalproject.startech.models.Category;
import finalproject.startech.repositories.CategoryRepository;
import finalproject.startech.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categories = categoryRepository.findAll().stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categories;
    }

    @Override
    public void createCategory(CategoryCreateDto categoryCreateDto) {
        Category category = modelMapper.map(categoryCreateDto, Category.class);
        categoryRepository.save(category);
    }
}
