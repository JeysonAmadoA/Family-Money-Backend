package JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.Expenses.ExpenseDto;
import JeysonAmadoA.FamilyMoney.Dto.Expenses.ExpenseUpsertDto;
import JeysonAmadoA.FamilyMoney.Exceptions.General.DeleteException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.StoreException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.UpdateException;

public interface ExpenseServiceInterface {

    ExpenseDto storeExpense(ExpenseUpsertDto expenseUpsertDto) throws StoreException;

    ExpenseDto getById(Long id) throws GetException;

    ExpenseDto updateExpense(Long id, ExpenseUpsertDto expenseUpsertDto) throws UpdateException;

    boolean deleteExpense(Long id) throws DeleteException;
}
