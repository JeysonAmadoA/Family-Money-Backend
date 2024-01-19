package JeysonAmadoA.FamilyMoney.Dto.Users;

import JeysonAmadoA.FamilyMoney.Utilities.Security.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String lastName;

    private LocalDate birthDay;

    private String email;

    private Role role;
}
