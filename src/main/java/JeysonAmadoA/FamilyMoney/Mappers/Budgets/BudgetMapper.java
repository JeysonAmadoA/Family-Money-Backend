package JeysonAmadoA.FamilyMoney.Mappers.Budgets;

import JeysonAmadoA.FamilyMoney.Dto.Budgets.BudgetDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.BudgetEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class BudgetMapper extends BaseMapper<BudgetDto, BudgetEntity> {

    public BudgetMapper() {
        super(BudgetDto.class, BudgetEntity.class);
    }
}
