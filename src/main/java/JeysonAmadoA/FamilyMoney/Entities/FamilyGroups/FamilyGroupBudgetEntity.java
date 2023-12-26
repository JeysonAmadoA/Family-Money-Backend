package JeysonAmadoA.FamilyMoney.Entities.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted_at is NULL")
@Table(name = "family_group_budgets")
@Getter
@Setter
public class FamilyGroupBudgetEntity extends BaseEntity {

    @Column(name = "budget_name")
    private String budgetName;

    @NotNull(message = "Debe ingresar un tipo de grupo")
    @Column(name = "period_id", nullable = false)
    private Long periodId;

    @ManyToOne
    @JoinColumn(name = "period_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PeriodEntity period;

    @Column
    private float percentage;

    @Column
    private String category;

}
