package finalproject.startech.dtos.servicedtos;

import finalproject.startech.models.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ServiceCreateDto {
    private Long id;
    private String name;
    private String description;
    private String icon;
    private Long categoryId;
}
