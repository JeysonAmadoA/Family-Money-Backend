package JeysonAmadoA.FamilyMoney.Http.Controllers.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.Budgets.BudgetDto;
import JeysonAmadoA.FamilyMoney.Dto.Budgets.BudgetUpsertDto;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups.BudgetServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    private final BudgetServiceInterface budgetService;

    public BudgetController(BudgetServiceInterface budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody BudgetUpsertDto budgetUpsertDto){
        try{
            BudgetDto savedBudget = budgetService.storeBudget(budgetUpsertDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBudget);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping("/{budgetId}")
    public ResponseEntity<?> getBudget(@PathVariable Long budgetId){
        try {
            BudgetDto budget = budgetService.getById(budgetId);
            return budget != null ? ResponseEntity.status(HttpStatus.OK).body(budget)
                    :ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gasto no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PatchMapping("update/{budgetId}")
    public ResponseEntity<?> update(@PathVariable Long budgetId, @RequestBody BudgetUpsertDto budgetUpsertDto){
        try {
            BudgetDto updatedFamilyBudget = budgetService.updateBudget(budgetId, budgetUpsertDto);
            return updatedFamilyBudget != null ? ResponseEntity.status(HttpStatus.OK).body(updatedFamilyBudget)
                    :ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gasto no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @DeleteMapping("delete/{budgetId}")
    public ResponseEntity<?> delete(@PathVariable Long budgetId){
        try {
            boolean isDeleted = budgetService.deleteBudget(budgetId);
            return isDeleted ? ResponseEntity.status(HttpStatus.OK).body("Gasto eliminado")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gasto no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
