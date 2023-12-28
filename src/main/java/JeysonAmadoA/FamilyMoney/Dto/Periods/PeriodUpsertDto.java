package JeysonAmadoA.FamilyMoney.Dto.Periods;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PeriodUpsertDto {

    private String periodName;

    private Long familyGroupId;

    private LocalDate startDate;

    private LocalDate endDate;
}
