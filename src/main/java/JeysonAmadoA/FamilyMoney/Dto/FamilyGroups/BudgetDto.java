package JeysonAmadoA.FamilyMoney.Dto.FamilyGroups;

import lombok.Data;

@Data
public class BudgetDto {

    private String budgetName;

    private Long periodId;

    private float percentage;

    private String category;
}
