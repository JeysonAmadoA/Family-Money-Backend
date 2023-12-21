package JeysonAmadoA.FamilyMoney.Services.Users;

import JeysonAmadoA.FamilyMoney.Dto.Users.UpdatePasswordDto;
import JeysonAmadoA.FamilyMoney.Dto.Users.UserDto;
import JeysonAmadoA.FamilyMoney.Entities.Users.UserEntity;
import JeysonAmadoA.FamilyMoney.Exceptions.DeleteUserException;
import JeysonAmadoA.FamilyMoney.Exceptions.UpdateUserException;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.Users.UserServiceInterface;
import JeysonAmadoA.FamilyMoney.Mappers.Users.UserMapper;
import JeysonAmadoA.FamilyMoney.Repositories.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static JeysonAmadoA.FamilyMoney.Helpers.AuthHelper.getUserWhoActingId;
import static JeysonAmadoA.FamilyMoney.Helpers.UserHelper.verifyNewPassword;
import static JeysonAmadoA.FamilyMoney.Helpers.UserHelper.verifyOldPassword;

@Service
public class UserService implements UserServiceInterface {

    private final UserMapper userMapper;

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepo) {
        this.userMapper = userMapper;
        this.userRepo = userRepo;
    }

    @Override
    public UserDto getUserById(Long userId) {
        UserEntity userFound = userRepo.findById(userId).orElse(null);
        return userFound != null ? userMapper.toDto(userFound) : null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> allUsers = userRepo.findAll();
        return allUsers.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> filterUsersByEmail(String email) {
        List<UserEntity> usersByEmail = userRepo.findByEmailLike(email);
        return usersByEmail.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> filterUsersByNameOrLastname(String entrySearch) {
        List<UserEntity> usersByName = userRepo.findByNameLikeOrLastNameLike(entrySearch);
        return usersByName.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) throws UpdateUserException {
        UserEntity userUpdated;
        try {
            UserEntity userFound = userRepo.findById(userId).orElse(null);
            if (userFound != null){
                userUpdated = userMapper.update(userFound, userDto);
                userUpdated.commitUpdate(getUserWhoActingId());
                userRepo.save(userUpdated);
            }
            else return null;
        } catch (Exception e){
            throw new UpdateUserException(e.getMessage());
        }
        return userMapper.toDto(userUpdated);
    }

    @Override
    public UserDto updatePassword(UpdatePasswordDto updatePasswordDto, Long userId) throws UpdateUserException {
        UserEntity userUpdated;
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            UserEntity userFound = userRepo.findById(userId).orElse(null);
            if (userFound != null &&
                verifyOldPassword(updatePasswordDto.getPassword(), userFound.getPassword(), encoder)){
                verifyNewPassword(updatePasswordDto);
                String newPasswordEncoded = encoder.encode(updatePasswordDto.getNewPassword());
                userFound.setPassword(newPasswordEncoded);
                userFound.commitUpdate(getUserWhoActingId());
                userUpdated = userRepo.save(userFound);
            }
            else return null;
        } catch (Exception e){
            throw new UpdateUserException(e.getMessage());
        }
        return userMapper.toDto(userUpdated);
    }

    @Override
    public boolean deleteUser(Long userId) throws DeleteUserException {
        try {
            UserEntity userFound = userRepo.findById(userId).orElse(null);
            if (userFound != null){
                userFound.commitDelete(getUserWhoActingId());
                userRepo.save(userFound);
                return true;
            }
            else return false;

        } catch (Exception e){
            throw new DeleteUserException(e.getMessage());
        }
    }

    @Override
    public UserEntity getUserByEmail(String userEmail) {
        return userRepo.findByEmail(userEmail).orElse(null);
    }


}
