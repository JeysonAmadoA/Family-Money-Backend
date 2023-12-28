package JeysonAmadoA.FamilyMoney.Dto.Expenses;

import lombok.Data;

@Data
public class ExpenseDto {

    private Long id;

    private String expenseName;

    private float expense;

    private String category;
}
