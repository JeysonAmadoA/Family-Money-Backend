package JeysonAmadoA.FamilyMoney.Dto.Budgets;

import lombok.Data;

@Data
public class BudgetUpsertDto {

    private String budgetName;

    private Long periodId;

    private float percentage;

    private String category;
}
