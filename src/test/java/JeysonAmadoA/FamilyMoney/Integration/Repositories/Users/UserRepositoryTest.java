package JeysonAmadoA.FamilyMoney.Integration.Repositories.Users;

import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupEntity;
import JeysonAmadoA.FamilyMoney.Entities.Users.UserEntity;
import JeysonAmadoA.FamilyMoney.Factories.FamilyGroups.FamilyGroupFactory;
import JeysonAmadoA.FamilyMoney.Repositories.Users.UserRepository;
import JeysonAmadoA.FamilyMoney.Utilities.Security.Role;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import(FamilyGroupFactory.class)
public class UserRepositoryTest {

    private final UserRepository userRepo;

    private final FamilyGroupFactory familyGroupFactory;

    @Autowired
    public UserRepositoryTest(UserRepository userRepo, FamilyGroupFactory familyGroupFactory) {
        this.userRepo = userRepo;
        this.familyGroupFactory = familyGroupFactory;
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUserTest() {
        FamilyGroupEntity familyGroup = (FamilyGroupEntity) familyGroupFactory.create();

        Set<FamilyGroupEntity> familyGroups = new HashSet<>();
        familyGroups.add(familyGroup);

        LocalDate date = LocalDate.now();

        UserEntity user = UserEntity.builder()
                .name("Jeyson").lastName("Amado")
                .birthDay(date).password("password").familyGroups(familyGroups)
                .email("jeyson@example.com").role(Role.ADMIN).build();

        UserEntity userCreated = userRepo.save(user);
        assertNotNull(userCreated);
        assertThat(userCreated.getId()).isGreaterThanOrEqualTo(1L);
    }

    @Test
    @Order(2)
    public void findByIdUserTest() {
        UserEntity userFound = userRepo.findById(1L).orElse(null);
        assertNotNull(userFound);
        assertEquals(1L ,userFound.getId());
    }

    @Test
    @Order(3)
    public void findAllUsersTest() {
        List<UserEntity> users = userRepo.findAll();
        assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateUserTest() {
        UserEntity userFound = userRepo.findById(1L).orElse(null);
        assertNotNull(userFound);
        userFound.setPassword("password123");

        UserEntity userUpdated = userRepo.save(userFound);
        assertNotNull(userUpdated);
        assertEquals(1L ,userUpdated.getId());
        assertEquals("password123", userUpdated.getPassword());
    }

    @Test
    @Order(5)
    public void findByEmailLikeUserTest(){
        List<UserEntity> usersFound = userRepo.findByEmailLike("%jeyson%");
        UserEntity userFound = usersFound.get(0);

        assertNotNull(usersFound);
        assertThat(usersFound.size()).isGreaterThan(0);
        assertEquals(1L, userFound.getId());
        assertEquals("jeyson@example.com", userFound.getEmail());
    }

    @Test
    @Order(5)
    public void findByNameOrLastNameLikeUserTest(){
        List<UserEntity> usersFoundByName = userRepo.findByNameLikeOrLastNameLike("son");
        UserEntity userFoundByName = usersFoundByName.get(0);

        assertNotNull(userFoundByName);
        assertThat(usersFoundByName.size()).isGreaterThan(0);
        assertEquals(1L, userFoundByName.getId());
        assertEquals("Jeyson", userFoundByName.getName());


        List<UserEntity> usersFoundByLastname = userRepo.findByNameLikeOrLastNameLike("Am");
        UserEntity userFoundByLastname = usersFoundByName.get(0);

        assertNotNull(userFoundByLastname);
        assertThat(usersFoundByLastname.size()).isGreaterThan(0);
        assertEquals(1L, userFoundByLastname.getId());
        assertEquals("Amado", userFoundByLastname.getLastName());
    }
}
