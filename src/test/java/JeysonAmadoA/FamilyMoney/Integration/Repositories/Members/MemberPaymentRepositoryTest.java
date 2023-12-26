package JeysonAmadoA.FamilyMoney.Integration.Repositories.Members;

import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupBudgetEntity;
import JeysonAmadoA.FamilyMoney.Entities.Members.MemberEntity;
import JeysonAmadoA.FamilyMoney.Entities.Members.MemberPaymentEntity;
import JeysonAmadoA.FamilyMoney.Factories.FamilyGroups.FamilyGroupBudgetFactory;
import JeysonAmadoA.FamilyMoney.Factories.FamilyGroups.FamilyGroupFactory;
import JeysonAmadoA.FamilyMoney.Factories.FamilyGroups.PeriodFactory;
import JeysonAmadoA.FamilyMoney.Factories.Members.MemberFactory;
import JeysonAmadoA.FamilyMoney.Repositories.Members.MemberPaymentRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import({FamilyGroupBudgetFactory.class, MemberFactory.class, FamilyGroupFactory.class, PeriodFactory.class})
public class MemberPaymentRepositoryTest {

    private final MemberPaymentRepository memberPaymentRepo;

    private final FamilyGroupBudgetFactory budgetFactory;

    private final MemberFactory memberFactory;

    @Autowired
    public MemberPaymentRepositoryTest(MemberPaymentRepository memberPaymentRepo,
                                       FamilyGroupBudgetFactory familyGroupFactory,
                                       MemberFactory memberFactory) {
        this.memberPaymentRepo = memberPaymentRepo;
        this.budgetFactory = familyGroupFactory;
        this.memberFactory = memberFactory;
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveMemberPaymentTest() {
        MemberEntity member = (MemberEntity) memberFactory.create();
        FamilyGroupBudgetEntity budget = (FamilyGroupBudgetEntity) budgetFactory.create();

        MemberPaymentEntity memberPayment = MemberPaymentEntity
                .builder().memberId(member.getId()).budgetId(budget.getId())
                .amount(58000).build();

        MemberPaymentEntity memberSaved = memberPaymentRepo.save(memberPayment);

        assertNotNull(memberSaved);
        assertThat(memberSaved.getId()).isGreaterThanOrEqualTo(1L);
    }

    @Test
    @Order(2)
    public void findByIdMemberPaymentTest() {
        MemberPaymentEntity memberFound = memberPaymentRepo.findById(1L).orElse(null);
        assertNotNull(memberFound);
        assertEquals(1L ,memberFound.getId());
    }

    @Test
    @Order(3)
    public void findAllMemberPaymentsTest() {
        List<MemberPaymentEntity> members = memberPaymentRepo.findAll();
        assertThat(members.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateMemberPaymentTest() {
        MemberPaymentEntity memberFound = memberPaymentRepo.findById(1L).orElse(null);
        assertNotNull(memberFound);
        memberFound.setAmount(55000);

        MemberPaymentEntity memberUpdated = memberPaymentRepo.save(memberFound);
        assertNotNull(memberUpdated);
        assertEquals(1L ,memberUpdated.getId());
        assertEquals(55000, memberUpdated.getAmount());
    }
}
