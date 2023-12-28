package JeysonAmadoA.FamilyMoney.Interfaces.Services.Members;

import JeysonAmadoA.FamilyMoney.Dto.Members.MemberDto;
import JeysonAmadoA.FamilyMoney.Dto.Members.MemberUpsertDto;
import JeysonAmadoA.FamilyMoney.Exceptions.General.DeleteException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.StoreException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.UpdateException;

import java.util.List;

public interface MemberServiceInterface {

    MemberDto storeMember(MemberUpsertDto memberUpsertDto) throws StoreException;

    MemberDto getById(Long id) throws GetException;

    MemberDto updateMember(Long id, MemberUpsertDto memberUpsertDto) throws UpdateException;

    boolean deleteMember(Long id) throws DeleteException;

}
