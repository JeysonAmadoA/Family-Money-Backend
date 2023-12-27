package JeysonAmadoA.FamilyMoney.Services.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupTypeEntity;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups.FamilyGroupTypeServiceInterface;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.FamilyGroupTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class FamilyGroupTypeService implements FamilyGroupTypeServiceInterface {

    private final FamilyGroupTypeRepository typeRepo;

    public FamilyGroupTypeService(FamilyGroupTypeRepository typeRepo) {
        this.typeRepo = typeRepo;
    }

    @Override
    public FamilyGroupTypeEntity filterById(Long typeId) {
        return typeRepo.findById(typeId).orElse(null);
    }
}
