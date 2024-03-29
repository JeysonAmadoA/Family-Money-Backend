package JeysonAmadoA.FamilyMoney.Interfaces.Services.Auth;

import JeysonAmadoA.FamilyMoney.Dto.Auth.JwtAuthenticationDto;
import JeysonAmadoA.FamilyMoney.Dto.Auth.LoginDto;
import JeysonAmadoA.FamilyMoney.Dto.Auth.RegisterUserDto;
import JeysonAmadoA.FamilyMoney.Dto.Users.UserDto;
import JeysonAmadoA.FamilyMoney.Exceptions.Users.RegisterUserException;

public interface AuthServiceInterface {

    UserDto registerUser(RegisterUserDto registerUserDto) throws RegisterUserException;

    UserDto loginUser(LoginDto loginDto);

    JwtAuthenticationDto getJwtAuthenticationDto();
}
