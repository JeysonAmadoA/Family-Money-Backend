package JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.BudgetEntity;
import JeysonAmadoA.FamilyMoney.Repositories.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepository extends BaseRepository<BudgetEntity, Long> {

    @Query("SELECT SUM(b.percentage) FROM BudgetEntity b WHERE b.period.id = :periodId")
    Optional<Float> getTotalPercentageByPeriodId(@Param("periodId") Long periodId);
}
