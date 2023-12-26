package JeysonAmadoA.FamilyMoney.Integration.Repositories.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupExpensesEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PeriodEntity;
import JeysonAmadoA.FamilyMoney.Factories.FamilyGroups.FamilyGroupFactory;
import JeysonAmadoA.FamilyMoney.Factories.FamilyGroups.PeriodFactory;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.FamilyGroupExpensesRepository;
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
public class FamilyGroupExpensesRepositoryTest {

    private final FamilyGroupExpensesRepository expensesRepo;

    private final PeriodFactory periodFactory;

    @Autowired
    public FamilyGroupExpensesRepositoryTest(FamilyGroupExpensesRepository expensesRepo, PeriodFactory periodFactory) {
        this.expensesRepo = expensesRepo;
        this.periodFactory = periodFactory;
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveExpenseTest() {
        PeriodEntity period = (PeriodEntity) periodFactory.create();


        FamilyGroupExpensesEntity expense = FamilyGroupExpensesEntity
                .builder().expenseName("Gastos").expense(60000)
                .category("Comida").periodId(period.getId()).build();

        FamilyGroupExpensesEntity expenseSaved = expensesRepo.save(expense);

        assertNotNull(expenseSaved);
        assertThat(expenseSaved.getId()).isGreaterThanOrEqualTo(1L);
    }

    @Test
    @Order(2)
    public void findByIdExpenseTest() {
        FamilyGroupExpensesEntity expenseFound = expensesRepo.findById(1L).orElse(null);
        assertNotNull(expenseFound);
        assertEquals(1L ,expenseFound.getId());
    }

    @Test
    @Order(3)
    public void findAllExpensesTest() {
        List<FamilyGroupExpensesEntity> periods = expensesRepo.findAll();
        assertThat(periods.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateExpenseTest() {
        FamilyGroupExpensesEntity expenseFound = expensesRepo.findById(1L).orElse(null);
        assertNotNull(expenseFound);
        expenseFound.setExpense(150000);

        FamilyGroupExpensesEntity expenseUpdated = expensesRepo.save(expenseFound);
        assertNotNull(expenseUpdated);
        assertEquals(1L ,expenseUpdated.getId());
        assertEquals(150000, expenseUpdated.getExpense());
    }
}
