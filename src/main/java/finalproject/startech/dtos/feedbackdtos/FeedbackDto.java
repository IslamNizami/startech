package finalproject.startech.dtos.feedbackdtos;


import finalproject.startech.models.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDto {

    private Long id;
    private String feedback;
    private String photoUrl;
    private Long userId;
    private String firstName;
    private String lastName;
}
