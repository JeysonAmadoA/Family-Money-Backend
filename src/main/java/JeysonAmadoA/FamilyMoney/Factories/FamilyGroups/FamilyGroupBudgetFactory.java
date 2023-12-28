package JeysonAmadoA.FamilyMoney.Factories.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.BudgetEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PeriodEntity;
import JeysonAmadoA.FamilyMoney.Factories.BaseFactory;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FamilyGroupBudgetFactory extends BaseFactory {

    private final BudgetRepository budgetRepo;

    private final PeriodFactory periodFactory;

    @Autowired
    public FamilyGroupBudgetFactory(BudgetRepository budgetRepo, PeriodFactory periodFactory) {
        this.budgetRepo = budgetRepo;
        this.periodFactory = periodFactory;
    }

    @Override
    public BaseEntity create() {
        PeriodEntity period = (PeriodEntity) periodFactory.create();

        return budgetRepo.save(BudgetEntity
                .builder().budgetName(faker.artist().name()).percentage(60)
                .category(faker.commerce().department()).periodId(period.getId()).build());
    }
}
