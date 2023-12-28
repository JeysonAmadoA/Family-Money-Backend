package JeysonAmadoA.FamilyMoney.Entities.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import JeysonAmadoA.FamilyMoney.Entities.Members.MemberEntity;
import JeysonAmadoA.FamilyMoney.Entities.Users.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "members_quantity", nullable = false)
    private int membersQuantity;

    @NotNull(message = "Debe ingresar un tipo de grupo")
    @Column(name = "family_group_type_id", nullable = false)
    private Long familyGroupTypeId;

    @ManyToOne
    @JoinColumn(name = "family_group_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private FamilyGroupTypeEntity familyGroupType;

    @NotNull(message = "Debe ingresar un apellido")
    @Column(name = "family_group_total_money")
    private float familyGroupTotalMoney;

    @ManyToMany(mappedBy = "familyGroups", fetch = FetchType.EAGER)
    private Set<UserEntity> users;

    @OneToMany(mappedBy = "familyGroup")
    private Set<MemberEntity> members;

}
