package JeysonAmadoA.FamilyMoney.Interfaces.Services.Payments;

import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentDto;
import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentUpsertDto;
import JeysonAmadoA.FamilyMoney.Exceptions.General.*;

public interface PaymentServiceInterface {

    PaymentDto storePayment(PaymentUpsertDto paymentUpsertDto) throws StoreException, DataIncompleteException;

    PaymentDto getById(Long id) throws GetException, NotFoundException;

    PaymentDto updatePayment(Long id, PaymentUpsertDto paymentUpsertDto) throws UpdateException, NotFoundException, DataIncompleteException;

    boolean deletePayment(Long id) throws DeleteException, NotFoundException;

}
