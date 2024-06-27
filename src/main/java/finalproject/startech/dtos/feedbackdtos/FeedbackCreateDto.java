package finalproject.startech.dtos.feedbackdtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackCreateDto {

    private String feedback;
    private Long userId;
    private String photoUrl;
}
