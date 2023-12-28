package JeysonAmadoA.FamilyMoney.Services.Members;

import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentDto;
import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentUpsertDto;
import JeysonAmadoA.FamilyMoney.Entities.Members.MemberPaymentEntity;
import JeysonAmadoA.FamilyMoney.Exceptions.General.DeleteException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.StoreException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.UpdateException;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.Members.MemberPaymentServiceInterface;
import JeysonAmadoA.FamilyMoney.Mappers.Payments.PaymentMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Payments.PaymentUpsertMapper;
import JeysonAmadoA.FamilyMoney.Repositories.Members.MemberPaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static JeysonAmadoA.FamilyMoney.Helpers.AuthHelper.getUserWhoActingId;

@Service
public class MemberPaymentService implements MemberPaymentServiceInterface {


    private final MemberPaymentRepository paymentRepo;

    private final PaymentMapper paymentMapper;

    private final PaymentUpsertMapper upsertMapper;

    @Autowired
    public MemberPaymentService(MemberPaymentRepository paymentRepo, PaymentMapper paymentMapper, PaymentUpsertMapper upsertMapper) {
        this.paymentRepo = paymentRepo;
        this.paymentMapper = paymentMapper;
        this.upsertMapper = upsertMapper;
    }

    @Transactional
    @Override
    public PaymentDto storePayment(PaymentUpsertDto paymentUpsertDto) throws StoreException {
        try {
            MemberPaymentEntity newPayment = upsertMapper.toEntity(paymentUpsertDto);
            newPayment.commitCreate(getUserWhoActingId());
            MemberPaymentEntity storedPayment = paymentRepo.saveAndFlush(newPayment);
            return getById(storedPayment.getId());
        } catch (Exception e){
            throw new StoreException(e.getMessage());
        }
    }

    @Override
    public PaymentDto getById(Long id) throws GetException {
        try {
            MemberPaymentEntity foundPayment = paymentRepo.findById(id).orElse(null);
            return foundPayment == null ? null : paymentMapper.toDto(foundPayment);
        } catch (Exception e){
            throw new GetException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public PaymentDto updatePayment(Long id, PaymentUpsertDto paymentUpsertDto) throws UpdateException {
        try {
            MemberPaymentEntity foundPayment = paymentRepo.findById(id).orElse(null);
            if (foundPayment != null){
                MemberPaymentEntity member = upsertMapper.update(paymentUpsertDto, foundPayment);
                member.commitUpdate(getUserWhoActingId());
                MemberPaymentEntity updatedPayment = paymentRepo.save(member);
                return paymentMapper.toDto(updatedPayment);
            } else return null;
        } catch (Exception e){
            throw new UpdateException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean deletePayment(Long id) throws DeleteException {
        try {
            MemberPaymentEntity foundPayment = paymentRepo.findById(id).orElse(null);
            if (foundPayment != null){
                foundPayment.commitDelete(getUserWhoActingId());
                paymentRepo.save(foundPayment);
                return true;
            } else return false;
        } catch (Exception e){
            throw new DeleteException(e.getMessage());
        }
    }
}
