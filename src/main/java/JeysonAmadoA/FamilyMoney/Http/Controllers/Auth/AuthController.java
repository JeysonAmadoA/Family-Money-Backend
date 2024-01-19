package JeysonAmadoA.FamilyMoney.Http.Controllers.Auth;

import JeysonAmadoA.FamilyMoney.Dto.Auth.JwtAuthenticationDto;
import JeysonAmadoA.FamilyMoney.Dto.Auth.LoginDto;
import JeysonAmadoA.FamilyMoney.Dto.Auth.RegisterUserDto;
import JeysonAmadoA.FamilyMoney.Dto.Users.UserDto;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.Auth.AuthServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static JeysonAmadoA.FamilyMoney.Helpers.AuthHelper.verifyRegisterPasswords;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceInterface authService;

    @Autowired
    public AuthController(AuthServiceInterface authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUserDto) {
        try {
            verifyRegisterPasswords(registerUserDto);
            UserDto userCreated = authService.registerUser(registerUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            UserDto userLogged = authService.loginUser(loginDto);
            JwtAuthenticationDto jwtAuthentication = authService.getJwtAuthenticationDto();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", jwtAuthentication.getToken());
            return ResponseEntity.status(HttpStatus.OK)
                    .headers(headers)
                    .body(userLogged);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
