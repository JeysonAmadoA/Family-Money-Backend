package JeysonAmadoA.FamilyMoney.Entities.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted_at is NULL")
@Table(name = "expenses")
@Getter
@Setter
public class ExpenseEntity extends BaseEntity {

    @Column(name = "expense_name")
    private String expenseName;

    @NotNull(message = "Debe ingresar un tipo de grupo")
    @Column(name = "period_id", nullable = false)
    private Long periodId;

    @ManyToOne
    @JoinColumn(name = "period_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PeriodEntity period;

    @Column
    private float expense;

    @Column
    private String category;
}
