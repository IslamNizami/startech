package finalproject.startech.dtos.blogdtos;

import finalproject.startech.dtos.categorydtos.CategoryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BlogRecentDto {
    private Long id;
    private String title;
    private Date createdDate;
    private String seoUrl;
    private String photoUrl;
}
