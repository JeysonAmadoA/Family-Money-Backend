package JeysonAmadoA.FamilyMoney.Unit.Dto.Users;

import JeysonAmadoA.FamilyMoney.Dto.Users.UserDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class UserDtoTest {

    @Test
    public void settersAndGettersTest(){

        LocalDate date = LocalDate.now();

        UserDto user = new UserDto();
        user.setName("Jeyson");
        user.setLastName("Amado");
        user.setBirthDay(LocalDate.now());
        user.setEmail("jeyson@example.com");

        assertEquals("Jeyson", user.getName());
        assertEquals("Amado", user.getLastName());
        assertEquals(date, user.getBirthDay());
        assertEquals("jeyson@example.com", user.getEmail());
    }
}
