package JeysonAmadoA.FamilyMoney.Dto.Budgets;

import lombok.Data;

@Data
public class BudgetDto {

    private Long id;

    private String budgetName;

    private float percentage;

    private String category;
}
