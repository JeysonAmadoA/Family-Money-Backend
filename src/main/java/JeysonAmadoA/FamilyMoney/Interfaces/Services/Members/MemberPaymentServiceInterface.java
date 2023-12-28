package JeysonAmadoA.FamilyMoney.Interfaces.Services.Members;

import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentDto;
import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentUpsertDto;
import JeysonAmadoA.FamilyMoney.Exceptions.General.DeleteException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.StoreException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.UpdateException;

public interface MemberPaymentServiceInterface {

    PaymentDto storePayment(PaymentUpsertDto paymentUpsertDto) throws StoreException;

    PaymentDto getById(Long id) throws GetException;

    PaymentDto updatePayment(Long id, PaymentUpsertDto paymentUpsertDto) throws UpdateException;

    boolean deletePayment(Long id) throws DeleteException;
}
