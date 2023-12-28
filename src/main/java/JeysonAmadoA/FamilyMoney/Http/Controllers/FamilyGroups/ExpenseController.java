package JeysonAmadoA.FamilyMoney.Http.Controllers.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.Expenses.ExpenseDto;
import JeysonAmadoA.FamilyMoney.Dto.Expenses.ExpenseUpsertDto;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups.ExpenseServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseServiceInterface expenseService;

    public ExpenseController(ExpenseServiceInterface expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody ExpenseUpsertDto expenseUpsertDto){
        try{
            ExpenseDto savedExpense = expenseService.storeExpense(expenseUpsertDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedExpense);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<?> getExpense(@PathVariable Long expenseId){
        try {
            ExpenseDto expense = expenseService.getById(expenseId);
            return expense != null ? ResponseEntity.status(HttpStatus.OK).body(expense)
                    :ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gasto no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PatchMapping("update/{expenseId}")
    public ResponseEntity<?> update(@PathVariable Long expenseId, @RequestBody ExpenseUpsertDto expenseUpsertDto){
        try {
            ExpenseDto updatedFamilyExpense = expenseService.updateExpense(expenseId, expenseUpsertDto);
            return updatedFamilyExpense != null ? ResponseEntity.status(HttpStatus.OK).body(updatedFamilyExpense)
                    :ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gasto no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @DeleteMapping("delete/{expenseId}")
    public ResponseEntity<?> delete(@PathVariable Long expenseId){
        try {
            boolean isDeleted = expenseService.deleteExpense(expenseId);
            return isDeleted ? ResponseEntity.status(HttpStatus.OK).body("Gasto eliminado")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gasto no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
