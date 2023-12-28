package JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.ExpenseEntity;
import JeysonAmadoA.FamilyMoney.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends BaseRepository<ExpenseEntity, Long> {
}
