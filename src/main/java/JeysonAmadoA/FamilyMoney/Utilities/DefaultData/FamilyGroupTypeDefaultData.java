package JeysonAmadoA.FamilyMoney.Utilities.DefaultData;

import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupTypeEntity;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.FamilyGroupTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FamilyGroupTypeDefaultData implements CommandLineRunner {

    private final FamilyGroupTypeRepository groupTypeRepo;

    @Autowired
    public FamilyGroupTypeDefaultData(FamilyGroupTypeRepository groupTypeRepo) {
        this.groupTypeRepo = groupTypeRepo;
    }

    @Override
    public void run(String... args) {
        FamilyGroupTypeEntity expenseType = FamilyGroupTypeEntity.builder().name("Presupuesto").build();
        FamilyGroupTypeEntity budgetType = FamilyGroupTypeEntity.builder().name("Gastos").build();

        groupTypeRepo.save(expenseType);
        groupTypeRepo.save(budgetType);
    }
}
