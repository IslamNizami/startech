package finalproject.startech.models;


import finalproject.startech.dtos.userdtos.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "feedback",length = Integer.MAX_VALUE)
    private String feedback;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
