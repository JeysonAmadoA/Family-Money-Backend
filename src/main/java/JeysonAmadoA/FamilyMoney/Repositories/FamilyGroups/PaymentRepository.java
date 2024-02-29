package JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentsFromGroupDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PaymentEntity;
import JeysonAmadoA.FamilyMoney.Repositories.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends BaseRepository<PaymentEntity, Long> {

    @Query("SELECT new JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentsFromGroupDto (" +
            "p.member.memberName, " +
            "p.budget.budgetName, " +
            "p.amount, " +
            "p.budget.period.periodName, " +
            "p.budget.category, " +
            "p.createdAt, " +
            "p.userWhoCreatedId) " +
            "FROM PaymentEntity p " +
            "LEFT JOIN p.budget b " +
            "LEFT JOIN b.period per " +
            "WHERE per.familyGroupId = :familyGroupId")
    List<PaymentsFromGroupDto> findPaymentsByFamilyGroupId(@Param("familyGroupId") Long familyGroupId);

}
