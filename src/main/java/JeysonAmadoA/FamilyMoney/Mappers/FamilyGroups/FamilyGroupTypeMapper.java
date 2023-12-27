package JeysonAmadoA.FamilyMoney.Mappers.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.FamilyGroups.FamilyGroupTypeDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupTypeEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class FamilyGroupTypeMapper extends BaseMapper<FamilyGroupTypeDto, FamilyGroupTypeEntity> {

    public FamilyGroupTypeMapper() {
        super(FamilyGroupTypeDto.class, FamilyGroupTypeEntity.class);
    }


}
