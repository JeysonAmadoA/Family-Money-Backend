package JeysonAmadoA.FamilyMoney.Dto.Expenses;

import lombok.Data;

@Data
public class ExpenseUpsertDto {

    private String expenseName;

    private Long periodId;

    private float expense;

    private String category;
}
