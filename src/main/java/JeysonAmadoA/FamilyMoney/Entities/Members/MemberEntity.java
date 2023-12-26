package JeysonAmadoA.FamilyMoney.Entities.Members;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupEntity;
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
@Table(name = "members")
@Getter
@Setter
public class MemberEntity extends BaseEntity {

    @Column(name = "member_name")
    private String memberName;

    @NotNull(message = "Debe ingresar un grupo familiar")
    @Column(name = "family_group_id", nullable = false)
    private Long familyGroupId;

    @ManyToOne
    @JoinColumn(name = "family_group_id", referencedColumnName = "id", insertable = false, updatable = false)
    private FamilyGroupEntity familyGroup;

    @Column(name = "member_rol")
    private String memberRol;

    @Column(name = "economic_contribution")
    private float economicContribution;
}
