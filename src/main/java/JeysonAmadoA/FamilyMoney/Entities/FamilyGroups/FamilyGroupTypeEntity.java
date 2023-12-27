package JeysonAmadoA.FamilyMoney.Entities.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted_at is NULL")
@Table(name = "family_group_types")
@Data
public class FamilyGroupTypeEntity extends BaseEntity {

    @Column
    private String name;
}
