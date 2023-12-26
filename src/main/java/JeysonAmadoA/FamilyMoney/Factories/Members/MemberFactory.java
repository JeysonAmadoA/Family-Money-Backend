package JeysonAmadoA.FamilyMoney.Factories.Members;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupEntity;
import JeysonAmadoA.FamilyMoney.Entities.Members.MemberEntity;
import JeysonAmadoA.FamilyMoney.Factories.BaseFactory;
import JeysonAmadoA.FamilyMoney.Factories.FamilyGroups.FamilyGroupFactory;
import JeysonAmadoA.FamilyMoney.Repositories.Members.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberFactory extends BaseFactory {

    private final MemberRepository memberRepo;

    private final FamilyGroupFactory familyGroupFactory;

    @Autowired
    public MemberFactory(MemberRepository memberRepo, FamilyGroupFactory familyGroupFactory) {
        this.memberRepo = memberRepo;
        this.familyGroupFactory = familyGroupFactory;
    }

    @Override
    public BaseEntity create() {
        FamilyGroupEntity familyGroup = (FamilyGroupEntity) familyGroupFactory.create();

        return memberRepo.save(MemberEntity.builder()
                .memberName(faker.dragonBall().character())
                .memberRol(faker.witcher().monster())
                .economicContribution(1700000)
                .familyGroupId(familyGroup.getId()).build());
    }
}
