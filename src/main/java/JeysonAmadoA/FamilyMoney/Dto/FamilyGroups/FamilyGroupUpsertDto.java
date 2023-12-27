package JeysonAmadoA.FamilyMoney.Dto.FamilyGroups;

import lombok.Data;

@Data
public class FamilyGroupUpsertDto {

    private String groupName;

    private int membersQuantity;

    private Long familyGroupTypeId;

    private float familyGroupTotalMoney;

}
