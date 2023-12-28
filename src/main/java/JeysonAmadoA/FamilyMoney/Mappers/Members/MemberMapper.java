package JeysonAmadoA.FamilyMoney.Mappers.Members;

import JeysonAmadoA.FamilyMoney.Dto.Members.MemberDto;
import JeysonAmadoA.FamilyMoney.Entities.Members.MemberEntity;
import JeysonAmadoA.FamilyMoney.Mappers.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper extends BaseMapper<MemberDto, MemberEntity> {

    public MemberMapper() {
        super(MemberDto.class, MemberEntity.class);
    }
}
