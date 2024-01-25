package JeysonAmadoA.FamilyMoney.Mappers.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.FamilyGroups.FamilyGroupDto;
import JeysonAmadoA.FamilyMoney.Dto.Members.MemberDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Members.MemberMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FamilyGroupMapper extends BaseMapper<FamilyGroupDto, FamilyGroupEntity> {

    private final FamilyGroupTypeMapper typeMapper;
    private final MemberMapper memberMapper;

    public FamilyGroupMapper(FamilyGroupTypeMapper typeMapper, MemberMapper memberMapper) {
        super(FamilyGroupDto.class, FamilyGroupEntity.class);
        this.typeMapper = typeMapper;
        this.memberMapper = memberMapper;
    }

    @Override
    public FamilyGroupDto toDto(FamilyGroupEntity entity) {
        FamilyGroupDto dto = new FamilyGroupDto();
        dto.setId(entity.getId());
        dto.setGroupName(entity.getGroupName());
        dto.setMembersQuantity(entity.getMembersQuantity());
        dto.setFamilyGroupTotalMoney(entity.getFamilyGroupTotalMoney());
        dto.setFamilyGroupType(typeMapper.toDto(entity.getFamilyGroupType()));
        dto.setFamilyGroupType(typeMapper.toDto(entity.getFamilyGroupType()));
        if (entity.getMembers() != null){
            List<MemberDto> membersDto = entity.getMembers().stream().map(memberMapper::toDto).toList();
            dto.setMembers(membersDto);
        }

        return dto;
    }

    @Override
    public FamilyGroupEntity toEntity(FamilyGroupDto dto) {
        FamilyGroupEntity entity = super.toEntity(dto);
        entity.setFamilyGroupType(typeMapper.toEntity(dto.getFamilyGroupType()));
        return entity;
    }

}
