package JeysonAmadoA.FamilyMoney.Services.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.Expenses.ExpenseDto;
import JeysonAmadoA.FamilyMoney.Dto.Expenses.ExpenseUpsertDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.ExpenseEntity;
import JeysonAmadoA.FamilyMoney.Exceptions.General.DeleteException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.StoreException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.UpdateException;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups.ExpenseServiceInterface;
import JeysonAmadoA.FamilyMoney.Mappers.Expenses.ExpenseMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Expenses.ExpenseUpsertMapper;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.ExpenseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static JeysonAmadoA.FamilyMoney.Helpers.AuthHelper.getUserWhoActingId;

@Service
public class ExpenseService implements ExpenseServiceInterface {

    private final ExpenseRepository expenseRepo;

    private final ExpenseMapper expenseMapper;

    private final ExpenseUpsertMapper upsertMapper;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepo, ExpenseMapper expenseMapper, ExpenseUpsertMapper upsertMapper) {
        this.expenseRepo = expenseRepo;
        this.expenseMapper = expenseMapper;
        this.upsertMapper = upsertMapper;
    }

    @Transactional
    @Override
    public ExpenseDto storeExpense(ExpenseUpsertDto expenseUpsertDto) throws StoreException {
        try {
            ExpenseEntity newExpense = upsertMapper.toEntity(expenseUpsertDto);
            newExpense.commitCreate(getUserWhoActingId());
            ExpenseEntity storedExpense = expenseRepo.save(newExpense);
            return expenseMapper.toDto(storedExpense);
        } catch (Exception e){
            throw new StoreException(e.getMessage());
        }
    }

    @Override
    public ExpenseDto getById(Long id) throws GetException {
        try {
            ExpenseEntity foundExpense = expenseRepo.findById(id).orElse(null);
            return foundExpense == null ? null : expenseMapper.toDto(foundExpense);
        } catch (Exception e){
            throw new GetException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public ExpenseDto updateExpense(Long id, ExpenseUpsertDto expenseUpsertDto) throws UpdateException {
        try {
            ExpenseEntity foundExpense = expenseRepo.findById(id).orElse(null);
            if (foundExpense != null){
                ExpenseEntity member = upsertMapper.update(expenseUpsertDto, foundExpense);
                member.commitUpdate(getUserWhoActingId());
                ExpenseEntity updatedExpense = expenseRepo.save(member);
                return expenseMapper.toDto(updatedExpense);
            } else return null;
        } catch (Exception e){
            throw new UpdateException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean deleteExpense(Long id) throws DeleteException {
        try {
            ExpenseEntity foundExpense = expenseRepo.findById(id).orElse(null);
            if (foundExpense != null){
                foundExpense.commitDelete(getUserWhoActingId());
                expenseRepo.save(foundExpense);
                return true;
            } else return false;
        } catch (Exception e){
            throw new DeleteException(e.getMessage());
        }
    }
}
