package finalproject.startech.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
public class PasswordResetToken {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    private String token;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id",referencedColumnName = "id")
    private UserEntity user;

    private LocalDateTime expiryDate;
}
