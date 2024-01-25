package JeysonAmadoA.FamilyMoney.Http.Controllers.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.Periods.PeriodDto;
import JeysonAmadoA.FamilyMoney.Dto.Periods.PeriodUpsertDto;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups.PeriodServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/period")
public class PeriodController {

    private final PeriodServiceInterface periodService;

    public PeriodController(PeriodServiceInterface periodService) {
        this.periodService = periodService;
    }

    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody PeriodUpsertDto periodUpsertDto){
        try{
            PeriodDto savedPeriod = periodService.storePeriod(periodUpsertDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPeriod);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping("/{periodId}")
    public ResponseEntity<?> getPeriod(@PathVariable Long periodId){
        try {
            PeriodDto period = periodService.getById(periodId);
            return period != null ? ResponseEntity.status(HttpStatus.OK).body(period)
                    :ResponseEntity.status(HttpStatus.NOT_FOUND).body("Periodo no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @GetMapping("group/{groupId}")
    public ResponseEntity<?> getPeriodsByGroupId(@PathVariable Long groupId){
        try {
            List<PeriodDto> periodsFound = periodService.filterByGroupId(groupId);
            return ResponseEntity.status(HttpStatus.OK).body(periodsFound);
        } catch (GetException getException){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(getException.getMessage());
        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PatchMapping("update/{periodId}")
    public ResponseEntity<?> update(@PathVariable Long periodId, @RequestBody PeriodUpsertDto periodUpsertDto){
        try {
            PeriodDto updatedFamilyPeriod = periodService.updatePeriod(periodId, periodUpsertDto);
            return updatedFamilyPeriod != null ? ResponseEntity.status(HttpStatus.OK).body(updatedFamilyPeriod)
                    :ResponseEntity.status(HttpStatus.NOT_FOUND).body("Periodo no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @DeleteMapping("delete/{periodId}")
    public ResponseEntity<?> delete(@PathVariable Long periodId){
        try {
            boolean isDeleted = periodService.deletePeriod(periodId);
            return isDeleted ? ResponseEntity.status(HttpStatus.OK).body("Periodo eliminado")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Periodo no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
