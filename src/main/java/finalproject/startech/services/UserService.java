package finalproject.startech.services;

import finalproject.startech.dtos.authdtos.RegisterDto;
import finalproject.startech.dtos.userdtos.UserAddRoleDto;
import finalproject.startech.dtos.userdtos.UserDashboardListDto;
import finalproject.startech.dtos.userdtos.UserDto;
import finalproject.startech.dtos.userdtos.UserFeedbackDto;

import java.util.List;

public interface UserService {

    boolean register(RegisterDto register);
    boolean confirmEmail(String email, String token);
    List<UserDashboardListDto> getDashboardUsers();
    UserDto getUserById(Long id);
    void addRole(UserAddRoleDto userAddRole);
    void removeUser(Long id);

    List<UserFeedbackDto> getAllFeedbackUsers();
}
