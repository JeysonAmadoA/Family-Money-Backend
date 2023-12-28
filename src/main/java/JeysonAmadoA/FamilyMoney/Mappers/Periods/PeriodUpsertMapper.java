package JeysonAmadoA.FamilyMoney.Mappers.Periods;

import JeysonAmadoA.FamilyMoney.Dto.Periods.PeriodUpsertDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PeriodEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import org.springframework.stereotype.Component;

import static JeysonAmadoA.FamilyMoney.Helpers.MapperHelper.updateFieldIfNotNull;

@Component
public class PeriodUpsertMapper extends BaseMapper<PeriodUpsertDto, PeriodEntity> {

    public PeriodUpsertMapper() {
        super(PeriodUpsertDto.class, PeriodEntity.class);
    }

    @Override
    public PeriodEntity toEntity(PeriodUpsertDto dto) {
        return PeriodEntity.builder()
                .periodName(dto.getPeriodName())
                .familyGroupId(dto.getFamilyGroupId())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }

    public PeriodEntity update(PeriodUpsertDto periodUpsertDto, PeriodEntity periodEntity){
        updateFieldIfNotNull(periodUpsertDto.getPeriodName(), periodEntity::setPeriodName);
        updateFieldIfNotNull(periodUpsertDto.getStartDate(), periodEntity::setStartDate);
        updateFieldIfNotNull(periodUpsertDto.getEndDate(), periodEntity::setEndDate);
        return periodEntity;
    }
}
