package JeysonAmadoA.FamilyMoney.Dto.FamilyGroups;

import lombok.Data;

@Data
public class ExpenseDto {

    private String expenseName;

    private Long periodId;

    private float expense;

    private String category;
}
