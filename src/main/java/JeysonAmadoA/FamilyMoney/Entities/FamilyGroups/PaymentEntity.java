package JeysonAmadoA.FamilyMoney.Entities.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import JeysonAmadoA.FamilyMoney.Entities.Members.MemberEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Where;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted_at is NULL")
@Table(name = "payments")
@Getter
@Setter
public class PaymentEntity extends BaseEntity {

    @Column(name = "amount")
    private float amount;

    @Column(name = "observations")
    private String observations;

    @NotNull(message = "Debe ingresar un miembro")
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MemberEntity member;

    @Column(name = "expense_id")
    private Long expenseId;

    @ManyToOne
    @JoinColumn(name = "expense_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ExpenseEntity expense;

    @Column(name = "budget_id")
    private Long budgetId;

    @ManyToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id", insertable = false, updatable = false)
    private BudgetEntity budget;

}
