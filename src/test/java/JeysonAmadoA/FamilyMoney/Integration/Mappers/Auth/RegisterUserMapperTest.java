package JeysonAmadoA.FamilyMoney.Integration.Mappers.Auth;

import JeysonAmadoA.FamilyMoney.Dto.Auth.RegisterUserDto;
import JeysonAmadoA.FamilyMoney.Entities.Users.UserEntity;
import JeysonAmadoA.FamilyMoney.Mappers.Auth.RegisterUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

@SpringBootTest
public class RegisterUserMapperTest {

    private final RegisterUserMapper registerUserMapper;

    @Autowired
    public RegisterUserMapperTest(RegisterUserMapper registerUserMapper) {
        this.registerUserMapper = registerUserMapper;
    }


    @Test
    public void RegisterUserDtoToEntityTest()
    {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setName("Jeyson");
        registerUserDto.setLastName("Amado");
        LocalDate date = LocalDate.now();
        registerUserDto.setBirthDay(date);
        registerUserDto.setPassword("password");
        registerUserDto.setConfirmPassword("password");
        registerUserDto.setEmail("jeyson@example.com");

        UserEntity user = this.registerUserMapper.toEntity(registerUserDto);
        assertEquals(registerUserDto.getName(), user.getName());
        assertEquals(registerUserDto.getLastName(), user.getLastName());
        assertEquals(registerUserDto.getBirthDay(), user.getBirthDay());
        assertEquals(registerUserDto.getPassword(), user.getPassword());
        assertEquals(registerUserDto.getEmail(), user.getEmail());
    }

}
