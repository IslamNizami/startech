package finalproject.startech.services;

import finalproject.startech.dtos.authdtos.RegisterDto;
import finalproject.startech.dtos.userdtos.*;
import finalproject.startech.models.UserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean register(RegisterDto register);

    boolean confirmEmail(String email, String token);

    List<UserDashboardListDto> getDashboardUsers();

    UserDto getUserById(Long id);

    void addRole(UserAddRoleDto userAddRole);

    void removeUser(Long id);

    List<UserFeedbackDto> getAllFeedbackUsers();

    String sendPasswordResetEmail(UserEntity user);
    String generateResetToken(UserEntity user);

    boolean hasExpired(LocalDateTime expiryDate);

    List<MemberDto> getAllMembers();
}

