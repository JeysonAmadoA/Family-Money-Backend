package JeysonAmadoA.FamilyMoney.Factories.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PeriodEntity;
import JeysonAmadoA.FamilyMoney.Factories.BaseFactory;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PeriodFactory extends BaseFactory {

    private final PeriodRepository periodRepo;

    private final FamilyGroupFactory familyGroupFactory;

    @Autowired
    public PeriodFactory(PeriodRepository periodRepo, FamilyGroupFactory familyGroupFactory) {
        this.periodRepo = periodRepo;
        this.familyGroupFactory = familyGroupFactory;
    }

    @Override
    public BaseEntity create() {
        FamilyGroupEntity familyGroup = (FamilyGroupEntity) familyGroupFactory.create();

        LocalDate startDate = LocalDate.of(2023, 11, 23);
        LocalDate endDate = LocalDate.of(2023, 12, 23);

        return periodRepo.save(PeriodEntity.builder()
                .periodName("Noviembre-Diciembre").startDate(startDate)
                .endDate(endDate).familyGroupId(familyGroup.getId())
                .build());
    }
}
