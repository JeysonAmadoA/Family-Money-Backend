package JeysonAmadoA.FamilyMoney.Mappers.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.FamilyGroups.FamilyGroupDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class FamilyGroupMapper extends BaseMapper<FamilyGroupDto, FamilyGroupEntity> {

    private final FamilyGroupTypeMapper typeMapper;

    public FamilyGroupMapper(FamilyGroupTypeMapper typeMapper) {
        super(FamilyGroupDto.class, FamilyGroupEntity.class);
        this.typeMapper = typeMapper;
    }

    @Override
    public FamilyGroupDto toDto(FamilyGroupEntity entity) {
        FamilyGroupDto dto = new FamilyGroupDto();
        dto.setId(entity.getId());
        dto.setGroupName(entity.getGroupName());
        dto.setMembersQuantity(entity.getMembersQuantity());
        dto.setFamilyGroupTotalMoney(entity.getFamilyGroupTotalMoney());
        dto.setFamilyGroupType(typeMapper.toDto(entity.getFamilyGroupType()));
        return dto;
    }

    @Override
    public FamilyGroupEntity toEntity(FamilyGroupDto dto) {
        FamilyGroupEntity entity = super.toEntity(dto);
        entity.setFamilyGroupType(typeMapper.toEntity(dto.getFamilyGroupType()));
        return entity;
    }

}
