package JeysonAmadoA.FamilyMoney.Dto.Periods;

import JeysonAmadoA.FamilyMoney.Dto.Budgets.BudgetDto;
import JeysonAmadoA.FamilyMoney.Dto.Expenses.ExpenseDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PeriodDto {

    private Long id;

    private String periodName;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<BudgetDto> budgets;

    private List<ExpenseDto> expenses;
}
