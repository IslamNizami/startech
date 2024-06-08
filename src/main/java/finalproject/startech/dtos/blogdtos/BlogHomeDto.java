package finalproject.startech.dtos.blogdtos;


import finalproject.startech.dtos.categorydtos.CategoryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BlogHomeDto {
    private Long id;
    private String title;
    private String subTitle;
    private String author;
    private Date createdDate;
    private CategoryDto category;
    private String seoUrl;
}
