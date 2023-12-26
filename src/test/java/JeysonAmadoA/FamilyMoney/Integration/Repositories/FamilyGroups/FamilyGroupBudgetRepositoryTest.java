package JeysonAmadoA.FamilyMoney.Integration.Repositories.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupBudgetEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PeriodEntity;
import JeysonAmadoA.FamilyMoney.Factories.FamilyGroups.FamilyGroupFactory;
import JeysonAmadoA.FamilyMoney.Factories.FamilyGroups.PeriodFactory;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.FamilyGroupBudgetRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import({PeriodFactory.class, FamilyGroupFactory.class})
public class FamilyGroupBudgetRepositoryTest {

    private final FamilyGroupBudgetRepository budgetRepo;

    private final PeriodFactory periodFactory;

    @Autowired
    public FamilyGroupBudgetRepositoryTest(FamilyGroupBudgetRepository budgetRepo, PeriodFactory periodFactory) {
        this.budgetRepo = budgetRepo;
        this.periodFactory = periodFactory;
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveBudgetTest() {
        PeriodEntity period = (PeriodEntity) periodFactory.create();


        FamilyGroupBudgetEntity budget = FamilyGroupBudgetEntity
                .builder().budgetName("Gastos").percentage(60)
                .category("Prioritarios").periodId(period.getId()).build();

        FamilyGroupBudgetEntity budgetSaved = budgetRepo.save(budget);

        assertNotNull(budgetSaved);
        assertThat(budgetSaved.getId()).isGreaterThanOrEqualTo(1L);
    }

    @Test
    @Order(2)
    public void findByIdBudgetTest() {
        FamilyGroupBudgetEntity budgetFound = budgetRepo.findById(1L).orElse(null);
        assertNotNull(budgetFound);
        assertEquals(1L ,budgetFound.getId());
    }

    @Test
    @Order(3)
    public void findAllBudgetsTest() {
        List<FamilyGroupBudgetEntity> periods = budgetRepo.findAll();
        assertThat(periods.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateBudgetTest() {
        FamilyGroupBudgetEntity budgetFound = budgetRepo.findById(1L).orElse(null);
        assertNotNull(budgetFound);
        budgetFound.setPercentage(70);

        FamilyGroupBudgetEntity budgetUpdated = budgetRepo.save(budgetFound);
        assertNotNull(budgetUpdated);
        assertEquals(1L ,budgetUpdated.getId());
        assertEquals(70, budgetUpdated.getPercentage());
    }
}
