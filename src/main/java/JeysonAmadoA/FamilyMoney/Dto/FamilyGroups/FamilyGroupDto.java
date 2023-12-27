package JeysonAmadoA.FamilyMoney.Dto.FamilyGroups;

import lombok.Data;

@Data
public class FamilyGroupDto {

    private Long id;

    private String groupName;

    private int membersQuantity;

    private FamilyGroupTypeDto familyGroupType;

    private float familyGroupTotalMoney;

}
