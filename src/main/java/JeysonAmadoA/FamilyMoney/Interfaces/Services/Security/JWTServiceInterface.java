package JeysonAmadoA.FamilyMoney.Interfaces.Services.Security;

import JeysonAmadoA.FamilyMoney.Entities.Users.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTServiceInterface {

    String generateToken(UserEntity user);

    String generateRefreshToken(Map<String, Object> extraClaims, UserDetails user);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
