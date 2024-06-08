package finalproject.startech.dtos.blogdtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogUpdateDto {
    private Long id;
    private String title;
    private String subTitle;
    private String description;
    private String author;
    private String photoUrl;
    private Long CategoryId;
}
