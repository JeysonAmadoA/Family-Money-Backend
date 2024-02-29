package JeysonAmadoA.FamilyMoney.Dto.Payments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsFromGroupDto {

    private String memberName;

    private String budgetName;

    private float amount;

    private String periodName;

    private String category;

    private LocalDateTime createdAt;

    private Long userWhoCreatedId;
}
