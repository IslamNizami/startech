package finalproject.startech.dtos.blogdtos;


import finalproject.startech.dtos.tagdtos.TagDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BlogCreateDto {
    private String title;
    private String subTitle;
    private String description;
    private String photoUrl;
    private Long CategoryId;
    private Set<Long> tagIds;
    private String author;
}
