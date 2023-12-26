package JeysonAmadoA.FamilyMoney.Factories.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupBudgetEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PeriodEntity;
import JeysonAmadoA.FamilyMoney.Factories.BaseFactory;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.FamilyGroupBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FamilyGroupBudgetFactory extends BaseFactory {

    private final FamilyGroupBudgetRepository budgetRepo;

    private final PeriodFactory periodFactory;

    @Autowired
    public FamilyGroupBudgetFactory(FamilyGroupBudgetRepository budgetRepo, PeriodFactory periodFactory) {
        this.budgetRepo = budgetRepo;
        this.periodFactory = periodFactory;
    }

    @Override
    public BaseEntity create() {
        PeriodEntity period = (PeriodEntity) periodFactory.create();

        return budgetRepo.save(FamilyGroupBudgetEntity
                .builder().budgetName(faker.artist().name()).percentage(60)
                .category(faker.commerce().department()).periodId(period.getId()).build());
    }
}
