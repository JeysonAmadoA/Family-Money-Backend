package JeysonAmadoA.FamilyMoney.Mappers.Periods;

import JeysonAmadoA.FamilyMoney.Dto.Periods.PeriodDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PeriodEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class PeriodMapper extends BaseMapper<PeriodDto, PeriodEntity> {

    public PeriodMapper() {
        super(PeriodDto.class, PeriodEntity.class);
    }
}
