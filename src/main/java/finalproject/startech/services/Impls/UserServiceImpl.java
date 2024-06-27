package finalproject.startech.services.Impls;

import finalproject.startech.dtos.authdtos.RegisterDto;
import finalproject.startech.dtos.userdtos.*;
import finalproject.startech.models.PasswordResetToken;
import finalproject.startech.models.Role;
import finalproject.startech.models.UserEntity;
import finalproject.startech.repositories.PasswordResetTokenRepository;
import finalproject.startech.repositories.RoleRepository;
import finalproject.startech.repositories.UserRepository;
import finalproject.startech.services.EmailService;
import finalproject.startech.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
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

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

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
        findUser.setPhotoUrl(userAddRole.getPhotoUrl());
        findUser.setInstagram(userAddRole.getInstagram());
        findUser.setLinkedin(userAddRole.getLinkedin());
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

    @Override
    public String sendPasswordResetEmail(UserEntity user) {
        try {
            String resetLink = generateResetToken(user);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("islamnizami0046@gmail.com");
            message.setTo(user.getEmail());
            message.setSubject("Password Reset Request");
            message.setText("Please click the link to Reset your password: " + resetLink);
            javaMailSender.send(message);
            return "success";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    public String generateResetToken(UserEntity user) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime expiryDateTime = currentDateTime.plusMinutes(10);
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(uuid.toString());
        resetToken.setExpiryDate(expiryDateTime);
        resetToken.setUser(user);
        PasswordResetToken token = passwordResetTokenRepository.save(resetToken);
        if (token != null) {
            String resetUrl = "http://localhost:6060/resetPassword/" + resetToken.getToken();
            return resetUrl;
        }
        return "";
    }

    @Override
    public boolean hasExpired(LocalDateTime expiryDate) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return expiryDate.isAfter(currentDateTime);
    }

    @Override
    public List<MemberDto> getAllMembers() {
        List<UserEntity> members = userRepository.findByEmailConfirmedTrue();
        List<MemberDto> result = members.stream()
                .map(member -> modelMapper.map(member, MemberDto.class))
                .collect(Collectors.toList());
        return result;
    }
}
