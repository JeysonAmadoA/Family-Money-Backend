package JeysonAmadoA.FamilyMoney.Mappers.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.FamilyGroups.FamilyGroupUpsertDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupEntity;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups.FamilyGroupTypeServiceInterface;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import org.springframework.stereotype.Component;

import static JeysonAmadoA.FamilyMoney.Helpers.MapperHelper.updateFieldIfNotNull;
import static JeysonAmadoA.FamilyMoney.Helpers.MapperHelper.updateFieldIfNumberNotZero;

@Component
public class FamilyGroupUpsertMapper extends BaseMapper<FamilyGroupUpsertDto, FamilyGroupEntity> {

    private final FamilyGroupTypeServiceInterface typeService;

    public FamilyGroupUpsertMapper(FamilyGroupTypeServiceInterface typeService) {
        super(FamilyGroupUpsertDto.class, FamilyGroupEntity.class);
        this.typeService = typeService;
    }

    @Override
    public FamilyGroupEntity toEntity(FamilyGroupUpsertDto dto) {
        return FamilyGroupEntity.builder()
                .groupName(dto.getGroupName())
                .familyGroupTypeId(dto.getFamilyGroupTypeId())
                .membersQuantity(dto.getMembersQuantity())
                .familyGroupTotalMoney(dto.getFamilyGroupTotalMoney())
                .familyGroupType(typeService.filterById(dto.getFamilyGroupTypeId()))
                .build();
    }


    public FamilyGroupEntity update(FamilyGroupUpsertDto familyGroupDto, FamilyGroupEntity familyGroupEntity) throws GetException {
        updateFieldIfNumberNotZero(familyGroupDto.getMembersQuantity(), familyGroupEntity::setMembersQuantity);
        updateFieldIfNumberNotZero(familyGroupDto.getFamilyGroupTotalMoney(), familyGroupEntity::setFamilyGroupTotalMoney);
        updateFieldIfNotNull(familyGroupDto.getGroupName(), familyGroupEntity::setGroupName);
        updateFieldIfNotNull(familyGroupDto.getFamilyGroupTypeId(), id -> {
            familyGroupEntity.setFamilyGroupType(null);
            familyGroupEntity.setFamilyGroupTypeId(id);
            familyGroupEntity.setFamilyGroupType(typeService.filterById(id));
        });

        return familyGroupEntity;
    }

}
