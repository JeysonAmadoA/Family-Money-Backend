package JeysonAmadoA.FamilyMoney.Dto.Users;

import lombok.Data;

@Data
public class UpdatePasswordDto {

    private String password;

    private String newPassword;

    private String confirmPassword;
}
