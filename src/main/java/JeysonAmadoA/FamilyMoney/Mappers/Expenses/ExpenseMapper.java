package JeysonAmadoA.FamilyMoney.Mappers.Expenses;

import JeysonAmadoA.FamilyMoney.Dto.Expenses.ExpenseDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.ExpenseEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper extends BaseMapper<ExpenseDto, ExpenseEntity> {

    public ExpenseMapper() {
        super(ExpenseDto.class, ExpenseEntity.class);
    }
}
