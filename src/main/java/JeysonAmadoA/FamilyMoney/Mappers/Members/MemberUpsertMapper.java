package JeysonAmadoA.FamilyMoney.Mappers.Members;

import JeysonAmadoA.FamilyMoney.Dto.Members.MemberUpsertDto;
import JeysonAmadoA.FamilyMoney.Entities.Members.MemberEntity;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import org.springframework.stereotype.Component;

import static JeysonAmadoA.FamilyMoney.Helpers.MapperHelper.updateFieldIfNotNull;
import static JeysonAmadoA.FamilyMoney.Helpers.MapperHelper.updateFieldIfNumberNotZero;

@Component
public class MemberUpsertMapper extends BaseMapper<MemberUpsertDto, MemberEntity> {

    public MemberUpsertMapper() {
        super(MemberUpsertDto.class, MemberEntity.class);
    }

    @Override
    public MemberEntity toEntity(MemberUpsertDto dto){
        return MemberEntity.builder()
                .memberName(dto.getMemberName())
                .familyGroupId(dto.getFamilyGroupId())
                .memberRol(dto.getMemberRol())
                .economicContribution(dto.getEconomicContribution())
                .build();
    }

    public MemberEntity update(MemberUpsertDto memberUpsertDto, MemberEntity memberEntity) throws GetException {
        updateFieldIfNotNull(memberUpsertDto.getMemberName(), memberEntity::setMemberName);
        updateFieldIfNotNull(memberUpsertDto.getMemberRol(), memberEntity::setMemberRol);
        updateFieldIfNumberNotZero(memberUpsertDto.getEconomicContribution(), memberEntity::setEconomicContribution);
        return memberEntity;
    }
}
