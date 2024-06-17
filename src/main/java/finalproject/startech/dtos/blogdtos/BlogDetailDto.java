package finalproject.startech.dtos.blogdtos;


import finalproject.startech.dtos.categorydtos.CategoryDto;
import finalproject.startech.dtos.tagdtos.TagDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class BlogDetailDto {
    private Long id;
    private String title;
    private String subTitle;
    private String description;
    private String photoUrl;
    private String createdDate;
    private String updatedDate;
    private CategoryDto category;
    private Set<TagDto> tags;
}
