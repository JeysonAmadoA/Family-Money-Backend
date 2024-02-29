package JeysonAmadoA.FamilyMoney.Http.Controllers.Payments;

import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentDto;
import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentUpsertDto;
import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentsFromGroupDto;
import JeysonAmadoA.FamilyMoney.Exceptions.General.DataIncompleteException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.NotFoundException;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.Payments.PaymentServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentServiceInterface paymentService;

    public PaymentController(PaymentServiceInterface paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody PaymentUpsertDto paymentUpsertDto){
        try{
            PaymentDto savedPayment = paymentService.storePayment(paymentUpsertDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
        } catch (DataIncompleteException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getPayment(@PathVariable Long paymentId){
        try {
            PaymentDto payment = paymentService.getById(paymentId);
            return ResponseEntity.status(HttpStatus.OK).body(payment);
        } catch (NotFoundException exception){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PatchMapping("update/{paymentId}")
    public ResponseEntity<?> update(@PathVariable Long paymentId, @RequestBody PaymentUpsertDto paymentUpsertDto){
        try {
            PaymentDto updatedFamilyPayment = paymentService.updatePayment(paymentId, paymentUpsertDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedFamilyPayment);
        } catch (NotFoundException exception){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @DeleteMapping("delete/{paymentId}")
    public ResponseEntity<?> delete(@PathVariable Long paymentId){
        try {
            paymentService.deletePayment(paymentId);
            return ResponseEntity.status(HttpStatus.OK).body("Pago eliminado");
        } catch (NotFoundException exception){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping("paymentsFromGroup/{groupId}")
    public ResponseEntity<?> getPaymentsFromGroup(@PathVariable Long groupId){
        try {
            List<PaymentsFromGroupDto> payments = paymentService.getPaymentsFromGroup(groupId);
            return ResponseEntity.status(HttpStatus.OK).body(payments);
        } catch (GetException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
}
