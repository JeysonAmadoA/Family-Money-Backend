package JeysonAmadoA.FamilyMoney.Factories.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupTypeEntity;
import JeysonAmadoA.FamilyMoney.Factories.BaseFactory;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.FamilyGroupRepository;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.FamilyGroupTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FamilyGroupFactory extends BaseFactory {

    private final FamilyGroupTypeRepository groupTypeRepo;

    private final FamilyGroupRepository familyGroupRepo;

    @Autowired
    public FamilyGroupFactory(FamilyGroupTypeRepository groupTypeRepo, FamilyGroupRepository familyGroupRepo) {
        super();
        this.groupTypeRepo = groupTypeRepo;
        this.familyGroupRepo = familyGroupRepo;
    }

    @Override
    public BaseEntity create() {
        FamilyGroupTypeEntity familyGroupType = groupTypeRepo.save(FamilyGroupTypeEntity
                .builder().name(faker.pokemon().name()).build());

        return familyGroupRepo.save(FamilyGroupEntity
                .builder().familyGroupType(familyGroupType).groupName(faker.rickAndMorty().character())
                .familyGroupTotalMoney(20000).membersQuantity(3)
                .familyGroupTypeId(familyGroupType.getId()).build());
    }
}
