package JeysonAmadoA.FamilyMoney.Factories.Users;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupTypeEntity;
import JeysonAmadoA.FamilyMoney.Entities.Users.UserEntity;
import JeysonAmadoA.FamilyMoney.Factories.BaseFactory;
import JeysonAmadoA.FamilyMoney.Factories.FamilyGroups.FamilyGroupFactory;
import JeysonAmadoA.FamilyMoney.Repositories.Users.UserRepository;
import JeysonAmadoA.FamilyMoney.Utilities.Security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class UsersFactory extends BaseFactory {

    private final UserRepository userRepo;

    private final FamilyGroupFactory familyGroupFactory;

    @Autowired
    public UsersFactory(UserRepository userRepository, FamilyGroupFactory familyGroupFactory) {
        super();
        this.userRepo = userRepository;
        this.familyGroupFactory = familyGroupFactory;
    }

    @Override
    public BaseEntity create() {

        FamilyGroupEntity familyGroup = (FamilyGroupEntity) familyGroupFactory.create();
        Set<FamilyGroupEntity> familyGroups = new HashSet<>();
        familyGroups.add(familyGroup);

        return userRepo.save(UserEntity.builder().familyGroups(familyGroups)
                .name(faker.name().firstName()).lastName(faker.name().lastName())
                .email(faker.internet().emailAddress()).birthDay(LocalDate.now())
                .password(faker.internet().password()).role(Role.CUSTOMER).build());
    }
}
