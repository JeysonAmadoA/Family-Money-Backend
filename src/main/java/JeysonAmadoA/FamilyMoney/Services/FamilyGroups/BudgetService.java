package JeysonAmadoA.FamilyMoney.Services.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.Budgets.BudgetDto;
import JeysonAmadoA.FamilyMoney.Dto.Budgets.BudgetUpsertDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.BudgetEntity;
import JeysonAmadoA.FamilyMoney.Exceptions.General.*;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups.BudgetServiceInterface;
import JeysonAmadoA.FamilyMoney.Mappers.Budgets.BudgetUpsertMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Budgets.BudgetMapper;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.BudgetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static JeysonAmadoA.FamilyMoney.Helpers.AuthHelper.getUserWhoActingId;

@Service
public class BudgetService implements BudgetServiceInterface {

    private final BudgetRepository budgetRepo;

    private final BudgetMapper budgetMapper;

    private final BudgetUpsertMapper upsertMapper;

    @Autowired
    public BudgetService(BudgetRepository budgetRepo, BudgetMapper budgetMapper, BudgetUpsertMapper upsertMapper) {
        this.budgetRepo = budgetRepo;
        this.budgetMapper = budgetMapper;
        this.upsertMapper = upsertMapper;
    }

    @Transactional
    @Override
    public BudgetDto storeBudget(BudgetUpsertDto budgetUpsertDto) throws StoreException {
        try {
            BudgetEntity newBudget = upsertMapper.toEntity(budgetUpsertDto);
            newBudget.commitCreate(getUserWhoActingId());
            BudgetEntity storedBudget = budgetRepo.save(newBudget);
            return budgetMapper.toDto(storedBudget);
        } catch (Exception e){
            throw new StoreException(e.getMessage());
        }
    }

    @Override
    public BudgetDto getById(Long id) throws GetException {
        try {
            BudgetEntity foundBudget = budgetRepo.findById(id).orElse(null);
            return foundBudget == null ? null : budgetMapper.toDto(foundBudget);
        } catch (Exception e){
            throw new GetException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public BudgetDto updateBudget(Long id, BudgetUpsertDto budgetUpsertDto) throws UpdateException {
        try {
            BudgetEntity foundBudget = budgetRepo.findById(id).orElse(null);
            if (foundBudget != null){
                BudgetEntity member = upsertMapper.update(budgetUpsertDto, foundBudget);
                member.commitUpdate(getUserWhoActingId());
                BudgetEntity updatedBudget = budgetRepo.save(member);
                return budgetMapper.toDto(updatedBudget);
            } else return null;
        } catch (Exception e){
            throw new UpdateException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean deleteBudget(Long id) throws DeleteException {
        try {
            BudgetEntity foundBudget = budgetRepo.findById(id).orElse(null);
            if (foundBudget != null){
                foundBudget.commitDelete(getUserWhoActingId());
                budgetRepo.save(foundBudget);
                return true;
            } else return false;
        } catch (Exception e){
            throw new DeleteException(e.getMessage());
        }
    }
}
