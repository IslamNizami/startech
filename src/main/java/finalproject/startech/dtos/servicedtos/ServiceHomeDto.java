package finalproject.startech.dtos.servicedtos;

import finalproject.startech.dtos.categorydtos.CategoryDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ServiceHomeDto {
    private Long id;
    private String name;
    private String description;
    private String icon;
}
