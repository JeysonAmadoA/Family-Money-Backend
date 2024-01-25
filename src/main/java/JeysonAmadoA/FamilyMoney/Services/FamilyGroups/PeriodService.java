package JeysonAmadoA.FamilyMoney.Services.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.Periods.PeriodDto;
import JeysonAmadoA.FamilyMoney.Dto.Periods.PeriodUpsertDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.PeriodEntity;
import JeysonAmadoA.FamilyMoney.Exceptions.General.DeleteException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.StoreException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.UpdateException;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups.PeriodServiceInterface;
import JeysonAmadoA.FamilyMoney.Mappers.Periods.PeriodMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Periods.PeriodUpsertMapper;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.PeriodRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static JeysonAmadoA.FamilyMoney.Helpers.AuthHelper.getUserWhoActingId;

@Service
public class PeriodService implements PeriodServiceInterface {

    private final PeriodRepository periodRepo;

    private final PeriodMapper periodMapper;

    private final PeriodUpsertMapper upsertMapper;

    @Autowired
    public PeriodService(PeriodRepository periodRepo, PeriodMapper periodMapper, PeriodUpsertMapper upsertMapper) {
        this.periodRepo = periodRepo;
        this.periodMapper = periodMapper;
        this.upsertMapper = upsertMapper;
    }

    @Transactional
    @Override
    public PeriodDto storePeriod(PeriodUpsertDto periodUpsertDto) throws StoreException {
        try {
            PeriodEntity newPeriod = upsertMapper.toEntity(periodUpsertDto);
            newPeriod.commitCreate(getUserWhoActingId());
            PeriodEntity storedPeriod = periodRepo.save(newPeriod);
            return periodMapper.toDto(storedPeriod);
        } catch (Exception e){
            throw new StoreException(e.getMessage());
        }
    }

    @Override
    public PeriodDto getById(Long id) throws GetException {
        try {
            PeriodEntity foundPeriod = periodRepo.findById(id).orElse(null);
            return foundPeriod == null ? null : periodMapper.toDto(foundPeriod);
        } catch (Exception e){
            throw new GetException(e.getMessage());
        }
    }

    @Override
    public List<PeriodDto> filterByGroupId(Long id) throws GetException {
        Optional<List<PeriodEntity>> foundPeriod = periodRepo.findByFamilyGroupId(id);
        List<PeriodEntity> periods = foundPeriod.orElseThrow(() -> new GetException("No se encontraron periodos"));

        return periods.stream()
                .map(periodMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PeriodDto updatePeriod(Long id, PeriodUpsertDto periodUpsertDto) throws UpdateException {
        try {
            PeriodEntity foundPeriod = periodRepo.findById(id).orElse(null);
            if (foundPeriod != null){
                PeriodEntity member = upsertMapper.update(periodUpsertDto, foundPeriod);
                member.commitUpdate(getUserWhoActingId());
                PeriodEntity updatedPeriod = periodRepo.save(member);
                return periodMapper.toDto(updatedPeriod);
            } else return null;
        } catch (Exception e){
            throw new UpdateException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean deletePeriod(Long id) throws DeleteException {
        try {
            PeriodEntity foundPeriod = periodRepo.findById(id).orElse(null);
            if (foundPeriod != null){
                foundPeriod.commitDelete(getUserWhoActingId());
                periodRepo.save(foundPeriod);
                return true;
            } else return false;
        } catch (Exception e){
            throw new DeleteException(e.getMessage());
        }
    }
}
