package JeysonAmadoA.FamilyMoney.Services.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.FamilyGroups.FamilyGroupDto;
import JeysonAmadoA.FamilyMoney.Dto.FamilyGroups.FamilyGroupUpsertDto;
import JeysonAmadoA.FamilyMoney.Dto.Members.MemberDto;
import JeysonAmadoA.FamilyMoney.Entities.FamilyGroups.FamilyGroupEntity;
import JeysonAmadoA.FamilyMoney.Entities.Members.MemberEntity;
import JeysonAmadoA.FamilyMoney.Entities.Users.UserEntity;
import JeysonAmadoA.FamilyMoney.Exceptions.General.DeleteException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.StoreException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.UpdateException;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups.FamilyGroupsServiceInterface;
import JeysonAmadoA.FamilyMoney.Mappers.FamilyGroups.FamilyGroupMapper;
import JeysonAmadoA.FamilyMoney.Mappers.FamilyGroups.FamilyGroupUpsertMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Members.MemberMapper;
import JeysonAmadoA.FamilyMoney.Repositories.FamilyGroups.FamilyGroupRepository;
import JeysonAmadoA.FamilyMoney.Repositories.Users.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static JeysonAmadoA.FamilyMoney.Helpers.AuthHelper.getUserWhoActingId;

@Service
public class FamilyGroupsService implements FamilyGroupsServiceInterface {

    private final UserRepository userRepo;

    private final FamilyGroupRepository familyGroupRepo;

    private final FamilyGroupMapper familyGroupMapper;

    private final FamilyGroupUpsertMapper upsertMapper;

    private final MemberMapper memberMapper;

    public FamilyGroupsService(UserRepository userRepo,
                               FamilyGroupRepository familyGroupRepo,
                               FamilyGroupMapper familyGroupMapper,
                               FamilyGroupUpsertMapper upsertMapper, MemberMapper memberMapper) {
        this.userRepo = userRepo;
        this.familyGroupRepo = familyGroupRepo;
        this.familyGroupMapper = familyGroupMapper;
        this.upsertMapper = upsertMapper;
        this.memberMapper = memberMapper;
    }

    @Transactional
    @Override
    public FamilyGroupDto storeFamilyGroup(FamilyGroupUpsertDto familyGroupDto) throws StoreException {
        try {
            UserEntity actualUser = userRepo.findById(Objects.requireNonNull(getUserWhoActingId()))
                    .orElseThrow(() -> new Exception("Usuario no encontrado"));

            FamilyGroupEntity newFamilyGroup = upsertMapper.toEntity(familyGroupDto);
            newFamilyGroup.commitCreate(actualUser.getId());
            FamilyGroupEntity storedGroup = familyGroupRepo.save(newFamilyGroup);
            storeUserHasGroupFamilies(actualUser, storedGroup);
            return familyGroupMapper.toDto(storedGroup);
        } catch (Exception e){
            throw new StoreException(e.getMessage());
        }
    }

    private void storeUserHasGroupFamilies(UserEntity user, FamilyGroupEntity familyGroup){
        Set<FamilyGroupEntity> groupEntities = user.getFamilyGroups();
        if (groupEntities == null) groupEntities = new HashSet<>();

        groupEntities.add(familyGroup);
        user.setFamilyGroups(groupEntities);
        user.commitUpdate(getUserWhoActingId());
        userRepo.save(user);
    }

    @Override
    public FamilyGroupDto getById(Long id) throws GetException {
        try {
            FamilyGroupEntity foundGroup = familyGroupRepo.findById(id).orElse(null);
            return foundGroup == null ? null : familyGroupMapper.toDto(foundGroup);
        } catch (Exception e){
            throw new GetException(e.getMessage());
        }
    }

    @Override
    public List<FamilyGroupDto> getFamilyGroups() throws GetException {
        try {
            UserEntity actualUser = userRepo.findById(Objects.requireNonNull(getUserWhoActingId()))
                    .orElseThrow(() -> new Exception("Usuario no encontrado"));

            Set<FamilyGroupEntity> userGroups = actualUser.getFamilyGroups();

            if (userGroups != null){
                return actualUser.getFamilyGroups()
                        .stream().map(familyGroupMapper::toDto)
                        .collect(Collectors.toList());
            } else return null;

        } catch (Exception e){
            throw new GetException(e.getMessage());
        }
    }

    @Override
    public List<MemberDto> getMembers(Long familyGroupId) throws GetException {
        try {
            FamilyGroupEntity familyGroup = familyGroupRepo.findById(familyGroupId)
                    .orElseThrow(() -> new EntityNotFoundException("No se encontró el grupo familiar"));

            return familyGroup.getMembers().stream()
                    .map(memberMapper::toDto)
                    .collect(Collectors.toList());

        } catch (Exception e){
            throw new GetException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public FamilyGroupDto updateFamilyGroup(Long id, FamilyGroupUpsertDto familyGroupDto) throws UpdateException {
        try {
            FamilyGroupEntity foundGroup = familyGroupRepo.findById(id).orElse(null);
            if (foundGroup != null){
                FamilyGroupEntity familyGroup = upsertMapper.update(familyGroupDto, foundGroup);
                familyGroup.commitUpdate(getUserWhoActingId());
                FamilyGroupEntity updatedFamilyGroup = familyGroupRepo.save(familyGroup);
                return familyGroupMapper.toDto(updatedFamilyGroup);
            } else return null;
        } catch (Exception e){
            throw new UpdateException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean deleteFamilyGroup(Long id) throws DeleteException {
        try {
            FamilyGroupEntity foundGroup = familyGroupRepo.findById(id).orElse(null);
            if (foundGroup != null){
                foundGroup.commitDelete(getUserWhoActingId());
                familyGroupRepo.save(foundGroup);
                return true;
            } else return false;
        } catch (Exception e){
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public FamilyGroupEntity filterById(Long id) throws GetException {
        return familyGroupRepo.findById(id).orElseThrow(() -> new GetException("Grupo familiar no encontrado"));
    }

    @Override
    public FamilyGroupEntity updateTotalMoney(Long id, float moneyAmount) throws GetException {
        FamilyGroupEntity familyGroup = filterById(id);
        familyGroup.sumFamilyGroupMoney(moneyAmount)
                .commitUpdate(getUserWhoActingId());

        return familyGroupRepo.save(familyGroup);
    }


}
