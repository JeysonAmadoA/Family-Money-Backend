package JeysonAmadoA.FamilyMoney.Mappers.Auth;

import JeysonAmadoA.FamilyMoney.Dto.Auth.RegisterUserDto;
import JeysonAmadoA.FamilyMoney.Entities.Users.UserEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserMapper extends BaseMapper<RegisterUserDto, UserEntity> {

    public RegisterUserMapper() {
        super(RegisterUserDto.class, UserEntity.class);
    }
}
