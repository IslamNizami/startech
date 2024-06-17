package finalproject.startech.services.Impls;

import finalproject.startech.dtos.roledtos.RoleCreateDto;
import finalproject.startech.dtos.roledtos.RoleDto;
import finalproject.startech.models.Role;
import finalproject.startech.repositories.RoleRepository;
import finalproject.startech.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RoleDto> getRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> result = roles.stream().map(role->modelMapper.map(role,RoleDto.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public void createRole(RoleCreateDto roleCreateDto) {
        Role role = modelMapper.map(roleCreateDto,Role.class);
        roleRepository.save(role);
    }
}
