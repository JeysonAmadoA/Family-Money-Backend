package JeysonAmadoA.FamilyMoney.Mappers.Users;

import JeysonAmadoA.FamilyMoney.Dto.Users.UserDto;
import JeysonAmadoA.FamilyMoney.Entities.Users.UserEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseMapper<UserDto, UserEntity> {

    public UserMapper() {
        super(UserDto.class, UserEntity.class);
    }

    public UserEntity update(UserEntity userEntity, UserDto userDto) {
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(userDto, userEntity);
        return userEntity;
    }
}
