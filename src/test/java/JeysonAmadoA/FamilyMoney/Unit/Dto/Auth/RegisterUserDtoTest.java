package JeysonAmadoA.FamilyMoney.Unit.Dto.Auth;

import JeysonAmadoA.FamilyMoney.Dto.Auth.RegisterUserDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class RegisterUserDtoTest {

    @Test
    public void settersAndGettersTest(){

        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setName("Jeyson");
        registerUserDto.setLastName("Amado");
        LocalDate date = LocalDate.now();
        registerUserDto.setBirthDay(date);
        registerUserDto.setPassword("password");
        registerUserDto.setConfirmPassword("password");
        registerUserDto.setEmail("jeyson@example.com");

        assertEquals("Jeyson", registerUserDto.getName());
        assertEquals("Amado", registerUserDto.getLastName());
        assertEquals(date, registerUserDto.getBirthDay());
        assertEquals("password", registerUserDto.getPassword());
        assertEquals("password", registerUserDto.getConfirmPassword());
        assertEquals("jeyson@example.com", registerUserDto.getEmail());
    }
}
