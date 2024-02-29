package JeysonAmadoA.FamilyMoney.Services.Members;

import JeysonAmadoA.FamilyMoney.Dto.Members.MemberDto;
import JeysonAmadoA.FamilyMoney.Dto.Members.MemberUpsertDto;
import JeysonAmadoA.FamilyMoney.Entities.Members.MemberEntity;
import JeysonAmadoA.FamilyMoney.Exceptions.General.DeleteException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.GetException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.StoreException;
import JeysonAmadoA.FamilyMoney.Exceptions.General.UpdateException;
import JeysonAmadoA.FamilyMoney.Exceptions.Members.RegisterMembersException;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups.FamilyGroupsServiceInterface;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.Members.MemberServiceInterface;
import JeysonAmadoA.FamilyMoney.Mappers.Members.MemberMapper;
import JeysonAmadoA.FamilyMoney.Mappers.Members.MemberUpsertMapper;
import JeysonAmadoA.FamilyMoney.Repositories.Members.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static JeysonAmadoA.FamilyMoney.Helpers.AuthHelper.getUserWhoActingId;
import static JeysonAmadoA.FamilyMoney.Helpers.MembersHelper.verifyMembersIsComplete;

@Service
public class MemberService implements MemberServiceInterface {

    private final MemberRepository memberRepo;

    private final MemberMapper memberMapper;

    private final MemberUpsertMapper upsertMapper;

    private final FamilyGroupsServiceInterface groupsService;

    @Autowired
    public MemberService(MemberRepository memberRepo,
                         MemberMapper memberMapper,
                         MemberUpsertMapper upsertMapper, FamilyGroupsServiceInterface groupsServiceInterface) {
        this.memberRepo = memberRepo;
        this.memberMapper = memberMapper;
        this.upsertMapper = upsertMapper;
        this.groupsService = groupsServiceInterface;
    }

    @Transactional
    @Override
    public MemberDto storeMember(MemberUpsertDto memberUpsertDto) throws StoreException, GetException, RegisterMembersException {
        int countMembersInGroup = memberRepo.countByFamilyGroupId(memberUpsertDto.getFamilyGroupId());
        int membersQuantityAllowed = groupsService.getFamilyGroupQuantityById(memberUpsertDto.getFamilyGroupId());
        verifyMembersIsComplete(countMembersInGroup, membersQuantityAllowed);
        try {
            MemberEntity newMember = upsertMapper.toEntity(memberUpsertDto);
            newMember.commitCreate(getUserWhoActingId());
            MemberEntity storedMember = memberRepo.save(newMember);
            return memberMapper.toDto(storedMember);
        } catch (Exception e){
            throw new StoreException(e.getMessage());
        }
    }

    @Override
    public MemberDto getById(Long id) throws GetException {
        try {
            MemberEntity foundMember = memberRepo.findById(id).orElse(null);
            return foundMember == null ? null : memberMapper.toDto(foundMember);
        } catch (Exception e){
            throw new GetException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public MemberDto updateMember(Long id, MemberUpsertDto memberUpsertDto) throws UpdateException {
        try {
            MemberEntity foundMember = memberRepo.findById(id).orElse(null);
            if (foundMember != null){
                MemberEntity member = upsertMapper.update(memberUpsertDto, foundMember);
                member.commitUpdate(getUserWhoActingId());
                MemberEntity updatedMember = memberRepo.save(member);
                return memberMapper.toDto(updatedMember);
            } else return null;
        } catch (Exception e){
            throw new UpdateException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean deleteMember(Long id) throws DeleteException {
        try {
            MemberEntity foundMember = memberRepo.findById(id).orElse(null);
            if (foundMember != null){
                foundMember.commitDelete(getUserWhoActingId());
                memberRepo.save(foundMember);
                return true;
            } else return false;
        } catch (Exception e){
            throw new DeleteException(e.getMessage());
        }
    }
}
