package JeysonAmadoA.FamilyMoney.Mappers.Expenses;

import JeysonAmadoA.FamilyMoney.Dto.Expenses.ExpenseUpsertDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.ExpenseEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import org.springframework.stereotype.Component;

import static JeysonAmadoA.FamilyMoney.Helpers.MapperHelper.updateFieldIfNotNull;
import static JeysonAmadoA.FamilyMoney.Helpers.MapperHelper.updateFieldIfNumberNotZero;

@Component
public class ExpenseUpsertMapper extends BaseMapper<ExpenseUpsertDto, ExpenseEntity> {

    public ExpenseUpsertMapper() {
        super(ExpenseUpsertDto.class, ExpenseEntity.class);
    }

    @Override
    public ExpenseEntity toEntity(ExpenseUpsertDto dto) {
        return ExpenseEntity.builder()
                .expenseName(dto.getExpenseName())
                .periodId(dto.getPeriodId())
                .expense(dto.getExpense())
                .category(dto.getCategory())
                .build();
    }

    public ExpenseEntity update(ExpenseUpsertDto periodUpsertDto, ExpenseEntity periodEntity){
        updateFieldIfNotNull(periodUpsertDto.getExpenseName(), periodEntity::setExpenseName);
        updateFieldIfNotNull(periodUpsertDto.getCategory(), periodEntity::setCategory);
        updateFieldIfNumberNotZero(periodUpsertDto.getExpense(), periodEntity::setExpense);
        return periodEntity;
    }
}
