package finalproject.startech.services.Impls;

import finalproject.startech.dtos.authdtos.RegisterDto;
import finalproject.startech.dtos.userdtos.UserAddRoleDto;
import finalproject.startech.dtos.userdtos.UserDashboardListDto;
import finalproject.startech.dtos.userdtos.UserDto;
import finalproject.startech.dtos.userdtos.UserFeedbackDto;
import finalproject.startech.models.Role;
import finalproject.startech.models.UserEntity;
import finalproject.startech.repositories.RoleRepository;
import finalproject.startech.repositories.UserRepository;
import finalproject.startech.services.EmailService;
import finalproject.startech.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean register(RegisterDto register) {
        UserEntity user = userRepository.findByEmail(register.getEmail());
        if  (user != null)
        {
            return false;
        }
        if (register.getPassword() == null || register.getPassword().length() < 8)
        {
            return false;
        }
        if (!register.getPassword().equals(register.getPasswordRepeat()))
        {
            return false;
        }

        String hashPassword = bCryptPasswordEncoder.encode(register.getPassword());
        String token = bCryptPasswordEncoder.encode(register.getEmail());
        UserEntity newUser = modelMapper.map(register,UserEntity.class);
        newUser.setConfirmationToken(token);
        newUser.setEmailConfirmed(false);
        newUser.setPassword(hashPassword);
        userRepository.save(newUser);
        emailService.sendConfirmationEmail(register.getEmail(),token);
        return true;
    }

    @Override
    public boolean confirmEmail(String email, String token) {
        UserEntity findUser = userRepository.findByEmail(email);
        if (findUser.getConfirmationToken().equals(token) && findUser != null) {
            findUser.setEmailConfirmed(true);
            userRepository.saveAndFlush(findUser);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDashboardListDto> getDashboardUsers() {
        List<UserEntity> findUsers = userRepository.findAll();
        List<UserDashboardListDto> users = findUsers.stream().map(user ->modelMapper.map(user, UserDashboardListDto.class)).collect(Collectors.toList());
        return users;
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity findUser =  userRepository.findById(id).orElseThrow();
        UserDto user = modelMapper.map(findUser, UserDto.class);
        return user;
    }

    @Override
    public void addRole(UserAddRoleDto userAddRole) {
        UserEntity findUser = userRepository.findByEmail(userAddRole.getEmail());
        List<Role> roles = roleRepository.findAll().stream().filter(x->x.getId() == userAddRole.getRoleId()).collect(Collectors.toList());
        findUser.setRoles(roles);
        userRepository.save(findUser);
    }

    @Override
    public void removeUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow();
        user.setEmailConfirmed(false);
        userRepository.save(user);
    }

    @Override
    public List<UserFeedbackDto> getAllFeedbackUsers() {
        List<UserEntity> findUsers = userRepository.findAll();
        List<UserFeedbackDto> users = findUsers.stream().map(user ->modelMapper.map(user, UserFeedbackDto.class)).collect(Collectors.toList());
        return users;
    }
}
