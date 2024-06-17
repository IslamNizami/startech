package finalproject.startech.services;

import finalproject.startech.dtos.roledtos.RoleCreateDto;
import finalproject.startech.dtos.roledtos.RoleDto;

import java.util.List;

public interface RoleService
{
    List<RoleDto> getRoles();
    void createRole(RoleCreateDto roleCreateDto);
}
