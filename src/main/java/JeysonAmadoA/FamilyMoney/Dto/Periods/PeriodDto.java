package JeysonAmadoA.FamilyMoney.Dto.Periods;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PeriodDto {

    private Long id;

    private String periodName;

    private LocalDate startDate;

    private LocalDate endDate;
}
