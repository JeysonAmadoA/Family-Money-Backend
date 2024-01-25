package JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PeriodEntity;
import JeysonAmadoA.FamilyMoney.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodRepository extends BaseRepository<PeriodEntity, Long> {

    Optional<List<PeriodEntity>> findByFamilyGroupId(Long groupId);
}
