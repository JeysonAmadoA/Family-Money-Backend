package JeysonAmadoA.FamilyMoney.Dto.FamilyGroups;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PeriodDto {

    private String periodName;

    private Long familyGroupId;

    private LocalDate startDate;

    private LocalDate endDate;
}
