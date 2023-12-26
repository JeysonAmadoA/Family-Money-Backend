package JeysonAmadoA.FamilyMoney.Interfaces.Services.Users;

import JeysonAmadoA.FamilyMoney.Dto.Users.UpdatePasswordDto;
import JeysonAmadoA.FamilyMoney.Dto.Users.UserDto;
import JeysonAmadoA.FamilyMoney.Entities.Users.UserEntity;
import JeysonAmadoA.FamilyMoney.Exceptions.Users.DeleteUserException;
import JeysonAmadoA.FamilyMoney.Exceptions.Users.UpdateUserException;

import java.util.List;

public interface UserServiceInterface {

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    List<UserDto> filterUsersByEmail(String email);

    List<UserDto> filterUsersByNameOrLastname(String entrySearch);

    UserDto updateUser(UserDto userDto, Long userId) throws UpdateUserException;

    UserDto updatePassword(UpdatePasswordDto updatePasswordDto, Long userId) throws UpdateUserException;

    boolean deleteUser(Long userId) throws DeleteUserException;

    UserEntity getUserByEmail(String userEmail);

}
