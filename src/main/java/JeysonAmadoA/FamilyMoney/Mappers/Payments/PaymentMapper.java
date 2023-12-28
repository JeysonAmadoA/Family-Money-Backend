package JeysonAmadoA.FamilyMoney.Mappers.Payments;

import JeysonAmadoA.FamilyMoney.Dto.Payments.PaymentDto;
import JeysonAmadoA.FamilyMoney.Entities.Members.MemberPaymentEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Budgets.BudgetMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Expenses.ExpenseMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Members.MemberMapper;
import JeysonAmadoA.FamilyMoney.Repositories.Members.MemberPaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper extends BaseMapper<PaymentDto, MemberPaymentEntity> {

//    private final MemberMapper memberMapper;
//
//    private final ExpenseMapper expenseMapper;
//
//    private final BudgetMapper budgetMapper;
//
//    private final MemberPaymentRepository memberPaymentRepository;


    public PaymentMapper() {
        super(PaymentDto.class, MemberPaymentEntity.class);
//        this.memberMapper = memberMapper;
//        this.expenseMapper = expenseMapper;
//        this.budgetMapper = budgetMapper;
//        this.memberPaymentRepository = memberPaymentRepository;
    }

//    @Override
//    public PaymentDto toDto(MemberPaymentEntity entity) {
//        MemberPaymentEntity memberPayment = memberPaymentRepository.findById(entity.getId()).orElseThrow(() -> new EntityNotFoundException("nocas"));
//        PaymentDto dto = new PaymentDto();
//        dto.setId(entity.getId());
//        dto.setMember(memberMapper.toDto(entity.getMember()));
//        dto.setAmount(entity.getAmount());
//        dto.setExpense(expenseMapper.toDto(entity.getExpense()));
//        dto.setBudget(budgetMapper.toDto(entity.getBudget()));
//        return dto;
//    }


}
