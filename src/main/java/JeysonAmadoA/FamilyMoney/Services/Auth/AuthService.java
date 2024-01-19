package JeysonAmadoA.FamilyMoney.Services.Auth;

import JeysonAmadoA.FamilyMoney.Dto.Auth.JwtAuthenticationDto;
import JeysonAmadoA.FamilyMoney.Dto.Auth.LoginDto;
import JeysonAmadoA.FamilyMoney.Dto.Auth.RegisterUserDto;
import JeysonAmadoA.FamilyMoney.Dto.Users.UserDto;
import JeysonAmadoA.FamilyMoney.Entities.Users.UserEntity;
import JeysonAmadoA.FamilyMoney.Exceptions.Users.RegisterUserException;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.Auth.AuthServiceInterface;
import JeysonAmadoA.FamilyMoney.Mappers.Auth.RegisterUserMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Users.UserMapper;
import JeysonAmadoA.FamilyMoney.Repositories.Users.UserRepository;
import JeysonAmadoA.FamilyMoney.Services.Security.JWTService;
import JeysonAmadoA.FamilyMoney.Utilities.Security.Role;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService implements AuthServiceInterface {


    @Getter
    private JwtAuthenticationDto jwtAuthenticationDto;


    private final RegisterUserMapper registerUserMapper;

    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final UserRepository userRepo;

    @Autowired
    public AuthService(RegisterUserMapper registerUserMapper, UserMapper userMapper, AuthenticationManager authenticationManager, JWTService jwtService, UserRepository userRepo) {
        this.registerUserMapper = registerUserMapper;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepo = userRepo;
    }

    @Override
    public UserDto registerUser(RegisterUserDto registerUserDto) throws RegisterUserException {
        UserEntity userRegistered;
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encryptedPassword = encoder.encode(registerUserDto.getPassword());

            UserEntity newUser = this.registerUserMapper.toEntity(registerUserDto);
            newUser.setPassword(encryptedPassword);
            newUser.setRole(Role.CUSTOMER);
            newUser.commitCreate();
            userRegistered = this.userRepo.save(newUser);
        } catch (Exception e) {
            throw new RegisterUserException(e.getMessage());
        }
        return this.userMapper.toDto(userRegistered);
    }

    public UserDto loginUser(LoginDto loginDto){
        authenticateUser(loginDto);
        UserEntity user = this.userRepo.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        setJwtAuthenticationDto(user);
        return userMapper.toDto(user);
    }

    private void authenticateUser(LoginDto loginDto){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword());

        authenticationManager.authenticate(authToken);
    }

    private void setJwtAuthenticationDto(UserEntity user) {
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        this.jwtAuthenticationDto = JwtAuthenticationDto
                .builder()
                .token(jwt).refreshToken(refreshToken)
                .build();
    }
}
