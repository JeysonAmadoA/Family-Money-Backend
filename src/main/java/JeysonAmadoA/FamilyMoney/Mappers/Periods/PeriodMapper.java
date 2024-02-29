package JeysonAmadoA.FamilyMoney.Mappers.Periods;

import JeysonAmadoA.FamilyMoney.Dto.Budgets.BudgetDto;
import JeysonAmadoA.FamilyMoney.Dto.Expenses.ExpenseDto;
import JeysonAmadoA.FamilyMoney.Dto.Periods.PeriodDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PeriodEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Budgets.BudgetMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Expenses.ExpenseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeriodMapper extends BaseMapper<PeriodDto, PeriodEntity> {

    private final BudgetMapper budgetMapper;
    private final ExpenseMapper expenseMapper;

    public PeriodMapper(BudgetMapper budgetMapper, ExpenseMapper expenseMapper) {
        super(PeriodDto.class, PeriodEntity.class);
        this.budgetMapper = budgetMapper;
        this.expenseMapper = expenseMapper;
    }

    @Override
    public PeriodDto toDto(PeriodEntity entity) {
        PeriodDto periodDto = new PeriodDto();
        periodDto.setId(entity.getId());
        periodDto.setPeriodName(entity.getPeriodName());
        periodDto.setStartDate(entity.getStartDate());
        periodDto.setEndDate(entity.getEndDate());
        periodDto.setFamilyGroupId(entity.getFamilyGroupId());
        if (entity.getBudgets() != null){
            List<BudgetDto> budgetsDto = entity.getBudgets().stream().map(budgetMapper::toDto).toList();
            periodDto.setBudgets(budgetsDto);
        }
        if (entity.getExpenses() != null){
            List<ExpenseDto> expensesDto = entity.getExpenses().stream().map(expenseMapper::toDto).toList();
            periodDto.setExpenses(expensesDto);
        }

        return periodDto;
    }
}
