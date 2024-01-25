package JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PaymentEntity;
import JeysonAmadoA.FamilyMoney.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends BaseRepository<PaymentEntity, Long> {
}
