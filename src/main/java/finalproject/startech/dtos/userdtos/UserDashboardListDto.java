package finalproject.startech.dtos.userdtos;


import finalproject.startech.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDashboardListDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean emailConfirmed;
    private String photoUrl;
    private List<Role> roles = new ArrayList<>();
}
