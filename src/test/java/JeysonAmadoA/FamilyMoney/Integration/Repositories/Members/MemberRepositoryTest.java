package JeysonAmadoA.FamilyMoney.Integration.Repositories.Members;

import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupEntity;
import JeysonAmadoA.FamilyMoney.Entities.Members.MemberEntity;
import JeysonAmadoA.FamilyMoney.Factories.FamilyGroups.FamilyGroupFactory;
import JeysonAmadoA.FamilyMoney.Repositories.Members.MemberRepository;
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
@Import(FamilyGroupFactory.class)
public class MemberRepositoryTest {

    private final MemberRepository memberRepo;

    private final FamilyGroupFactory familyGroupFactory;

    @Autowired
    public MemberRepositoryTest(MemberRepository memberRepo, FamilyGroupFactory familyGroupFactory) {
        this.memberRepo = memberRepo;
        this.familyGroupFactory = familyGroupFactory;
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveMemberTest() {
        FamilyGroupEntity familyGroup = (FamilyGroupEntity) familyGroupFactory.create();

        MemberEntity memberOne = MemberEntity.builder()
                .memberName("Hugo").memberRol("Hijo")
                .familyGroupId(familyGroup.getId()).economicContribution(2800000)
                .build();

        MemberEntity memberTwo = MemberEntity.builder()
                .memberName("Hugo").memberRol("Hijo")
                .familyGroupId(familyGroup.getId()).economicContribution(2800000)
                .build();

        MemberEntity memberSavedOne = memberRepo.save(memberOne);
        MemberEntity memberSavedTwo = memberRepo.save(memberTwo);

        assertNotNull(memberSavedOne);
        assertThat(memberSavedOne.getId()).isGreaterThanOrEqualTo(1L);
        assertNotNull(memberSavedTwo);
        assertThat(memberSavedTwo.getId()).isGreaterThanOrEqualTo(1L);
    }

    @Test
    @Order(2)
    public void findByIdMemberTest() {
        MemberEntity memberFound = memberRepo.findById(1L).orElse(null);
        assertNotNull(memberFound);
        assertEquals(1L ,memberFound.getId());
    }

    @Test
    @Order(3)
    public void findAllMembersTest() {
        List<MemberEntity> members = memberRepo.findAll();
        assertThat(members.size()).isGreaterThan(1);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateMemberTest() {
        MemberEntity memberFound = memberRepo.findById(1L).orElse(null);
        assertNotNull(memberFound);
        memberFound.setEconomicContribution(3500000);

        MemberEntity memberUpdated = memberRepo.save(memberFound);
        assertNotNull(memberUpdated);
        assertEquals(1L ,memberUpdated.getId());
        assertEquals(3500000, memberUpdated.getEconomicContribution());
    }
}
