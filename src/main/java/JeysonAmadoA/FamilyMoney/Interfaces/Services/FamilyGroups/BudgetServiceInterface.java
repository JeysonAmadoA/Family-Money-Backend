package JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.Budgets.BudgetDto;
import JeysonAmadoA.FamilyMoney.Dto.Budgets.BudgetUpsertDto;
import JeysonAmadoA.FamilyMoney.Exceptions.Budgets.RegisterBudgetException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.DeleteException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.StoreException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.UpdateException;

public interface BudgetServiceInterface {

    BudgetDto storeBudget(BudgetUpsertDto budgetUpsertDto) throws StoreException, RegisterBudgetException;

    BudgetDto getById(Long id) throws GetException;

    BudgetDto updateBudget(Long id, BudgetUpsertDto budgetUpsertDto) throws UpdateException;

    boolean deleteBudget(Long id) throws DeleteException;
}
