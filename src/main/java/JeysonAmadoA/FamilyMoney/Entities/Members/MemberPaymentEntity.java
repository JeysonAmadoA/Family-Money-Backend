package JeysonAmadoA.FamilyMoney.Entities.Members;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.BudgetEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.ExpenseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted_at is NULL")
@Table(name = "members_payment")
@Getter
@Setter
public class MemberPaymentEntity extends BaseEntity {

    @NotNull(message = "Debe ingresar un tipo de grupo")
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MemberEntity member;

    @Column(nullable = false)
    private float amount;

    @Column
    private String observations;

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
