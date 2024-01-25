package JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.Periods.PeriodDto;
import JeysonAmadoA.FamilyMoney.Dto.Periods.PeriodUpsertDto;
import JeysonAmadoA.FamilyMoney.Exceptions.General.DeleteException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.StoreException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.UpdateException;

import java.util.List;

public interface PeriodServiceInterface {

    PeriodDto storePeriod(PeriodUpsertDto periodUpsertDto) throws StoreException;

    PeriodDto getById(Long id) throws GetException;

    List<PeriodDto> filterByGroupId(Long id) throws GetException;

    PeriodDto updatePeriod(Long id, PeriodUpsertDto periodUpsertDto) throws UpdateException;

    boolean deletePeriod(Long id) throws DeleteException;
}
