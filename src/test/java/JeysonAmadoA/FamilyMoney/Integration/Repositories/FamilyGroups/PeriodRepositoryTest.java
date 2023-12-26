package JeysonAmadoA.FamilyMoney.Integration.Repositories.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PeriodEntity;
import JeysonAmadoA.FamilyMoney.Factories.FamilyGroups.FamilyGroupFactory;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.PeriodRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import(FamilyGroupFactory.class)
public class PeriodRepositoryTest{

    private final PeriodRepository periodRepository;

    private final FamilyGroupFactory familyGroupFactory;

    @Autowired
    public PeriodRepositoryTest(PeriodRepository periodRepository, FamilyGroupFactory familyGroupFactory) {
        this.periodRepository = periodRepository;
        this.familyGroupFactory = familyGroupFactory;
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveFamilyGroupTest() {
        FamilyGroupEntity familyGroup = (FamilyGroupEntity) familyGroupFactory.create();

        LocalDate startDate = LocalDate.of(2023,12,28);
        LocalDate endDate = LocalDate.of(2024,1,28);

        PeriodEntity period = PeriodEntity.builder()
                .familyGroupId(familyGroup.getId())
                .periodName("Enero-Febrero").startDate(startDate)
                .endDate(endDate).familyGroup(familyGroup).build();

        PeriodEntity periodSaved = periodRepository.save(period);

        assertNotNull(periodSaved);
        assertThat(periodSaved.getId()).isGreaterThanOrEqualTo(1L);
    }

    @Test
    @Order(2)
    public void findByIdFamilyGroupTest() {
        PeriodEntity periodFound = periodRepository.findById(1L).orElse(null);
        assertNotNull(periodFound);
        assertEquals(1L ,periodFound.getId());
    }

    @Test
    @Order(3)
    public void findAllFamilyGroupsTest() {
        List<PeriodEntity> periods = periodRepository.findAll();
        assertThat(periods.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateFamilyGroupTest() {
        PeriodEntity periodFound = periodRepository.findById(1L).orElse(null);
        assertNotNull(periodFound);
        periodFound.setPeriodName("Diciembre-Enero");

        PeriodEntity periodUpdated = periodRepository.save(periodFound);
        assertNotNull(periodUpdated);
        assertEquals(1L ,periodUpdated.getId());
        assertEquals("Diciembre-Enero", periodUpdated.getPeriodName());
    }

}
