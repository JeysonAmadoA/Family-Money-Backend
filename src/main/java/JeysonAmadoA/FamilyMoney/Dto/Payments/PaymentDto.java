package JeysonAmadoA.FamilyMoney.Dto.Payments;

import JeysonAmadoA.FamilyMoney.Dto.Budgets.BudgetDto;
import JeysonAmadoA.FamilyMoney.Dto.Expenses.ExpenseDto;
import JeysonAmadoA.FamilyMoney.Dto.Members.MemberDto;
import lombok.Data;

@Data
public class PaymentDto {

    private Long id;

//    private MemberDto member;

    private float amount;

    private String observations;

//    private ExpenseDto expense;

//    private BudgetDto budget;

}
