package finalproject.startech.dtos.userdtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddRoleDto {

    private String email;
    private Long roleId;
    private String photoUrl;
    private String instagram;
    private String linkedin;
}
