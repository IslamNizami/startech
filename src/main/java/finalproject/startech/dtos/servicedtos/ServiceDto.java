package finalproject.startech.dtos.servicedtos;

import finalproject.startech.dtos.categorydtos.CategoryDto;
import finalproject.startech.models.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ServiceDto {
    private Long id;
    private String name;
    private String description;
    private String icon;
    private Boolean isDeleted;
    private CategoryDto category;
}