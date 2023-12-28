package JeysonAmadoA.FamilyMoney.Dto.Members;

import lombok.Data;

@Data
public class MemberDto {

    private Long id;

    private String memberName;

    private String memberRol;

    private float economicContribution;
}
