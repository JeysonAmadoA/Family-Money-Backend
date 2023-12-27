package JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.FamilyGroups.FamilyGroupDto;
import JeysonAmadoA.FamilyMoney.Dto.FamilyGroups.FamilyGroupUpsertDto;
import JeysonAmadoA.FamilyMoney.Exceptions.General.DeleteException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.StoreException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.UpdateException;

import java.util.List;

public interface FamilyGroupsServiceInterface {

    FamilyGroupDto storeFamilyGroup(FamilyGroupUpsertDto familyGroupDto) throws StoreException;

    List<FamilyGroupDto> getFamilyGroups () throws GetException;

    FamilyGroupDto updateFamilyGroup(Long id, FamilyGroupUpsertDto familyGroupDto) throws UpdateException;

    boolean deleteFamilyGroup(Long id) throws DeleteException;

}
