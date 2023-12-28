package JeysonAmadoA.FamilyMoney.Dto.Payments;

import lombok.Data;

@Data
public class PaymentUpsertDto {

    private Long memberId;

    private float amount;

    private String observations;

    private Long expenseId;

    private Long budgetId;

}
