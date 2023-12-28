package JeysonAmadoA.FamilyMoney.Http.Controllers.Members;

import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentDto;
import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentUpsertDto;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.Members.MemberPaymentServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class MemberPaymentController {

    private final MemberPaymentServiceInterface paymentService;

    public MemberPaymentController(MemberPaymentServiceInterface paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody PaymentUpsertDto paymentUpsertDto){
        try{
            PaymentDto savedPayment = paymentService.storePayment(paymentUpsertDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getPayment(@PathVariable Long paymentId){
        try {
            PaymentDto payment = paymentService.getById(paymentId);
            return payment != null ? ResponseEntity.status(HttpStatus.OK).body(payment)
                    :ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gasto no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PatchMapping("update/{paymentId}")
    public ResponseEntity<?> update(@PathVariable Long paymentId, @RequestBody PaymentUpsertDto paymentUpsertDto){
        try {
            PaymentDto updatedFamilyPayment = paymentService.updatePayment(paymentId, paymentUpsertDto);
            return updatedFamilyPayment != null ? ResponseEntity.status(HttpStatus.OK).body(updatedFamilyPayment)
                    :ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gasto no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @DeleteMapping("delete/{paymentId}")
    public ResponseEntity<?> delete(@PathVariable Long paymentId){
        try {
            boolean isDeleted = paymentService.deletePayment(paymentId);
            return isDeleted ? ResponseEntity.status(HttpStatus.OK).body("Gasto eliminado")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gasto no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
