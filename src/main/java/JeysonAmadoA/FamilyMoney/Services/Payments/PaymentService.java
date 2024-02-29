package JeysonAmadoA.FamilyMoney.Services.Payments;

import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentDto;
import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentUpsertDto;
import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentsFromGroupDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PaymentEntity;
import JeysonAmadoA.FamilyMoney.Exceptions.General.*;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.Payments.PaymentServiceInterface;
import JeysonAmadoA.FamilyMoney.Mappers.Payments.PaymentMapper;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import static JeysonAmadoA.FamilyMoney.Helpers.AuthHelper.getUserWhoActingId;
import static JeysonAmadoA.FamilyMoney.Helpers.ValidatorHelper.validateNotNulls;

@Service
public class PaymentService implements PaymentServiceInterface {

    private final PaymentRepository paymentRepo;

    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepo = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    @Transactional
    public PaymentDto storePayment(PaymentUpsertDto paymentUpsertDto) throws StoreException, DataIncompleteException {
        validatePaymentDto(paymentUpsertDto);
        try {
            PaymentEntity newPayment = paymentMapper.toEntity(paymentUpsertDto);
            newPayment.commitCreate(getUserWhoActingId());
            PaymentEntity storedExpense = paymentRepo.save(newPayment);
            return paymentMapper.toDto(storedExpense);
        } catch (Exception e){
            throw new StoreException(e.getMessage());
        }
    }

    @Override
    public PaymentDto getById(Long id) throws NotFoundException {
        PaymentEntity paymentFound = paymentRepo.findById(id).orElseThrow(NotFoundException::new);
        return paymentMapper.toDto(paymentFound);
    }

    @Override
    @Transactional
    public PaymentDto updatePayment(Long id, PaymentUpsertDto paymentUpsertDto) throws UpdateException, NotFoundException, DataIncompleteException {
        PaymentEntity paymentFound = paymentRepo.findById(id).orElseThrow(NotFoundException::new);
        validatePaymentDto(paymentUpsertDto);
        try {
            PaymentEntity payment = paymentMapper.update(paymentFound, paymentUpsertDto);
            payment.commitUpdate(getUserWhoActingId());
            PaymentEntity updatedPayment = paymentRepo.save(payment);
            return paymentMapper.toDto(updatedPayment);
        } catch (Exception e){
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deletePayment(Long id) throws DeleteException, NotFoundException {
        PaymentEntity paymentFound = paymentRepo.findById(id).orElseThrow(NotFoundException::new);
        try {
            paymentFound.commitDelete(getUserWhoActingId());
            paymentRepo.save(paymentFound);
            return true;
        } catch (Exception e){
            throw new DeleteException(e.getMessage());
        }
    }

    private void validatePaymentDto(PaymentUpsertDto paymentUpsertDto) throws DataIncompleteException {
        String[] excludedFields = {"expenseId","budgetId"};
        validateNotNulls(paymentUpsertDto, excludedFields);
    }

    @Override
    public List<PaymentsFromGroupDto> getPaymentsFromGroup(Long groupId) throws GetException {
        try {
            return paymentRepo.findPaymentsByFamilyGroupId(groupId);
        } catch (Exception e){
            throw new GetException(e.getMessage());
        }
    }


}
