package JeysonAmadoA.FamilyMoney.Mappers.Payments;

import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentUpsertDto;
import JeysonAmadoA.FamilyMoney.Entities.Members.MemberPaymentEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import org.springframework.stereotype.Component;

import static JeysonAmadoA.FamilyMoney.Helpers.MapperHelper.updateFieldIfNumberNotZero;
import static JeysonAmadoA.FamilyMoney.Helpers.MapperHelper.updateFieldIfNotNull;

@Component
public class PaymentUpsertMapper extends BaseMapper<PaymentUpsertDto, MemberPaymentEntity> {

    public PaymentUpsertMapper() {
        super(PaymentUpsertDto.class, MemberPaymentEntity.class);
    }

    @Override
    public MemberPaymentEntity toEntity(PaymentUpsertDto dto) {
        return MemberPaymentEntity.builder()
                .memberId(dto.getMemberId())
                .amount(dto.getAmount())
                .observations(dto.getObservations())
                .budgetId(dto.getBudgetId())
                .expenseId(dto.getExpenseId())
                .build();
    }

    public MemberPaymentEntity update(PaymentUpsertDto paymentUpsertDto, MemberPaymentEntity paymentEntity){
        updateFieldIfNumberNotZero(paymentUpsertDto.getAmount(), paymentEntity::setAmount);
        updateFieldIfNotNull(paymentUpsertDto.getObservations(), paymentEntity::setObservations);
        return paymentEntity;
    }
}
