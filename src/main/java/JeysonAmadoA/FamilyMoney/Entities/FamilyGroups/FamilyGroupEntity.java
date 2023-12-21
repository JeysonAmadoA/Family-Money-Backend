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
@Table(name = "family_groups")
@Getter
@Setter
public class FamilyGroupEntity extends BaseEntity {

    @NotNull(message = "Debe ingresar un nombre")
    @NotBlank(message = "El campo nombre de grupo no puede estar vacío")
    @Column(name = "group_name")
    private String groupName;

    @NotNull(message = "Debe ingresar cantidad de miembros")
    @NotBlank(message = "El campo cantidad de miembros no puede estar vacío")
    @Column(name = "members_quantity", nullable = false)
    private int membersQuantity;

    @NotNull(message = "Debe ingresar un tipo de grupo")
    @NotBlank(message = "El campo tipo de grupo no puede estar vacío")
    @Column(name = "family_group_type_id", nullable = false)
    private Long familyGroupTypeId;

    @ManyToOne
    @JoinColumn(name = "family_group_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private FamilyGroupTypeEntity familyGroupType;

    @NotNull(message = "Debe ingresar un apellido")
    @NotBlank(message = "El campo apellido no puede estar vacío")
    @Column(name = "family_group_total_money", columnDefinition = "FLOAT DEFAULT 0.0")
    private float familyGroupTotalMoney;

}
