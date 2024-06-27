package finalproject.startech.dtos.userdtos;


import finalproject.startech.dtos.roledtos.RoleDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private String firstName;
    private String lastName;
    private String instagram;
    private String linkedin;
    private String photoUrl;
    private RoleDto role;
}
