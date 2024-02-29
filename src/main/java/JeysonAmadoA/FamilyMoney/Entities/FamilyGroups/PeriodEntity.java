package JeysonAmadoA.FamilyMoney.Entities.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted_at is NULL")
@Table(name = "period")
@Getter
@Setter
public class PeriodEntity extends BaseEntity {

    @NotNull(message = "Debe ingresar un nombre")
    @NotBlank(message = "El campo nombre no puede estar vacío")
    @Column(name = "period_name")
    private String periodName;

    @Column(name = "family_group_id", nullable = false)
    private Long familyGroupId;

    @ManyToOne
    @JoinColumn(name = "family_group_id", referencedColumnName = "id", insertable = false, updatable = false)
    private FamilyGroupEntity familyGroup;

    @NotNull(message = "Debe ingresar una fecha inicial")
    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    @NotNull(message = "Debe ingresar una fecha final")
    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;

    @OneToMany(mappedBy = "period")
    private Set<BudgetEntity> budgets;

    @OneToMany(mappedBy = "period")
    private Set<ExpenseEntity> expenses;

    public double getTotalPercentage(){
        return budgets.stream()
                .mapToDouble(BudgetEntity::getPercentage)
                .sum();
    }

}
