package JeysonAmadoA.FamilyMoney.Mappers.Budgets;

import JeysonAmadoA.FamilyMoney.Dto.Budgets.BudgetUpsertDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.BudgetEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import org.springframework.stereotype.Component;

import static JeysonAmadoA.FamilyMoney.Helpers.MapperHelper.updateFieldIfNotNull;
import static JeysonAmadoA.FamilyMoney.Helpers.MapperHelper.updateFieldIfNumberNotZero;

@Component
public class BudgetUpsertMapper extends BaseMapper<BudgetUpsertDto, BudgetEntity> {

    public BudgetUpsertMapper() {
        super(BudgetUpsertDto.class, BudgetEntity.class);
    }

    @Override
    public BudgetEntity toEntity(BudgetUpsertDto dto) {
        return BudgetEntity.builder()
                .budgetName(dto.getBudgetName())
                .periodId(dto.getPeriodId())
                .percentage(dto.getPercentage())
                .category(dto.getCategory())
                .build();
    }

    public BudgetEntity update(BudgetUpsertDto periodUpsertDto, BudgetEntity periodEntity){
        updateFieldIfNotNull(periodUpsertDto.getBudgetName(), periodEntity::setBudgetName);
        updateFieldIfNotNull(periodUpsertDto.getCategory(), periodEntity::setCategory);
        updateFieldIfNumberNotZero(periodUpsertDto.getPercentage(), periodEntity::setPercentage);
        return periodEntity;
    }
}
