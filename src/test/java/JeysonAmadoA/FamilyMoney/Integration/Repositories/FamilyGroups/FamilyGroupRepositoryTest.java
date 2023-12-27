package JeysonAmadoA.FamilyMoney.Integration.Repositories.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupTypeEntity;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.FamilyGroupRepository;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.FamilyGroupTypeRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FamilyGroupRepositoryTest {

    private final FamilyGroupRepository familyGroupRepo;

    private final FamilyGroupTypeRepository familyGroupTypeRepo;

    @Autowired
    public FamilyGroupRepositoryTest(FamilyGroupRepository familyGroupRepo, FamilyGroupTypeRepository familyGroupTypeRepo) {
        this.familyGroupRepo = familyGroupRepo;
        this.familyGroupTypeRepo = familyGroupTypeRepo;
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveFamilyGroupTest() {
        FamilyGroupTypeEntity type = FamilyGroupTypeEntity.builder().name("Type").build();
        FamilyGroupTypeEntity typeEntity = familyGroupTypeRepo.save(type);

        FamilyGroupEntity familyGroup = FamilyGroupEntity
                .builder().familyGroupTypeId(1L).groupName("Familia")
                .familyGroupTotalMoney(25000).membersQuantity(3)
                .familyGroupTypeId(typeEntity.getId()).build();

        FamilyGroupEntity groupEntity = familyGroupRepo.save(familyGroup);

        assertNotNull(groupEntity);
        assertThat(groupEntity.getId()).isGreaterThanOrEqualTo(1L);
    }

    @Test
    @Order(2)
    public void findByIdFamilyGroupTest() {
        FamilyGroupEntity groupFound = familyGroupRepo.findById(1L).orElse(null);
        assertNotNull(groupFound);
        assertEquals(1L ,groupFound.getId());
    }

    @Test
    @Order(3)
    public void findAllFamilyGroupsTest() {
        List<FamilyGroupEntity> groups = familyGroupRepo.findAll();
        assertThat(groups.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateFamilyGroupTest() {
        FamilyGroupEntity groupFound = familyGroupRepo.findById(1L).orElse(null);
        FamilyGroupTypeEntity otherType = familyGroupTypeRepo.save(FamilyGroupTypeEntity.builder().name("Type diferent").build());
        assertNotNull(groupFound);
        groupFound.setMembersQuantity(4);
        groupFound.setFamilyGroupType(otherType);

        FamilyGroupEntity groupUpdated = familyGroupRepo.save(groupFound);
        assertNotNull(groupUpdated);
        assertEquals(1L ,groupUpdated.getId());
        assertEquals(4, groupUpdated.getMembersQuantity());
    }
}
