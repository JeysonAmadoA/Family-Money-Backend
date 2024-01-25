package JeysonAmadoA.FamilyMoney.Mappers.Payments;

import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentDto;
import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentUpsertDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.BudgetEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.ExpenseEntity;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PaymentEntity;
import JeysonAmadoA.FamilyMoney.Entities.Members.MemberEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Budgets.BudgetMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Expenses.ExpenseMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Members.MemberMapper;
import org.springframework.stereotype.Component;

import static JeysonAmadoA.FamilyMoney.Helpers.MapperHelper.updateFieldIfNotNull;
import static JeysonAmadoA.FamilyMoney.Helpers.MapperHelper.updateFieldIfNumberNotZero;

@Component
public class PaymentMapper extends BaseMapper<PaymentDto, PaymentEntity> {

    private final MemberMapper memberMapper;
    private final BudgetMapper budgetMapper;
    private final ExpenseMapper expenseMapper;

    public PaymentMapper(MemberMapper memberMapper, BudgetMapper budgetMapper, ExpenseMapper expenseMapper) {
        super(PaymentDto.class, PaymentEntity.class);
        this.memberMapper = memberMapper;
        this.budgetMapper = budgetMapper;
        this.expenseMapper = expenseMapper;
    }

    @Override
    public PaymentDto toDto(PaymentEntity entity) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setId(entity.getId());
        paymentDto.setAmount(entity.getAmount());
        paymentDto.setObservations(entity.getObservations());
        MemberEntity member = entity.getMember();
        if(member != null) paymentDto.setMember(memberMapper.toDto(member));
        BudgetEntity budget = entity.getBudget();
        if(budget != null) paymentDto.setBudget(budgetMapper.toDto(budget));
        ExpenseEntity expense = entity.getExpense();
        if(expense != null) paymentDto.setExpense(expenseMapper.toDto(expense));
        return paymentDto;
    }

    public PaymentEntity toEntity(PaymentUpsertDto dto) {
        return PaymentEntity.builder()
                .amount(dto.getAmount())
                .observations(dto.getObservations())
                .memberId(dto.getMemberId())
                .budgetId(dto.getBudgetId())
                .expenseId(dto.getExpenseId())
                .build();
    }

    public PaymentEntity update(PaymentEntity paymentEntity, PaymentUpsertDto paymentDto){
        updateFieldIfNumberNotZero(paymentDto.getAmount(), paymentEntity::setAmount);
        updateFieldIfNotNull(paymentDto.getObservations(), paymentEntity::setObservations);
        updateFieldIfNotNull(paymentDto.getMemberId(), paymentEntity::setMemberId);
        updateFieldIfNotNull(paymentDto.getBudgetId(), paymentEntity::setBudgetId);
        updateFieldIfNotNull(paymentDto.getExpenseId(), paymentEntity::setExpenseId);
        return paymentEntity;
    }
}
