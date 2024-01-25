package JeysonAmadoA.FamilyMoney.Dto.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.Members.MemberDto;
import lombok.Data;

import java.util.List;

@Data
public class FamilyGroupDto {

    private Long id;

    private String groupName;

    private int membersQuantity;

    private FamilyGroupTypeDto familyGroupType;

    private float familyGroupTotalMoney;

    private List<MemberDto> members;

}
