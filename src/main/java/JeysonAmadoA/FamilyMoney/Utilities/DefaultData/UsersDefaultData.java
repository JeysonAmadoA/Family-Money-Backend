package JeysonAmadoA.FamilyMoney.Utilities.DefaultData;

import JeysonAmadoA.FamilyMoney.Entities.Users.UserEntity;
import JeysonAmadoA.FamilyMoney.Repositories.Users.UserRepository;
import JeysonAmadoA.FamilyMoney.Utilities.Security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UsersDefaultData implements CommandLineRunner {

    private final UserRepository userRepo;

    @Autowired
    public UsersDefaultData(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) {

        UserEntity userAdmin = UserEntity.builder()
                .name("admin").lastName("administrador")
                .email("admin@example.com").birthDay(LocalDate.of(2003,10,12))
                .password((new BCryptPasswordEncoder()).encode("admin123"))
                .role(Role.ADMIN)
                .build();

        userAdmin.commitCreate();

        UserEntity userCustomer = UserEntity.builder()
                .name("Satoru").lastName("Gojo")
                .email("Satoru@Gojo.com").birthDay(LocalDate.of(1990,7,14))
                .password((new BCryptPasswordEncoder()).encode("Geto123"))
                .role(Role.CUSTOMER)
                .build();

        userCustomer.commitCreate();

        userRepo.save(userAdmin);
        userRepo.save(userCustomer);
    }
}
