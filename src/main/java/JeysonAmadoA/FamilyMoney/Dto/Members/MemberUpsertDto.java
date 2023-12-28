package JeysonAmadoA.FamilyMoney.Dto.Members;

import lombok.Data;

@Data
public class MemberUpsertDto {

    private String memberName;

    private Long familyGroupId;

    private String memberRol;

    private float economicContribution;
}
