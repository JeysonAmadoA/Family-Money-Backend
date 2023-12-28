package JeysonAmadoA.FamilyMoney.Http.Controllers.Members;

import JeysonAmadoA.FamilyMoney.Dto.Members.MemberDto;
import JeysonAmadoA.FamilyMoney.Dto.Members.MemberUpsertDto;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.Members.MemberServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceInterface memberService;

    @Autowired
    public MemberController(MemberServiceInterface memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody MemberUpsertDto memberUpsertDto){
        try{
            MemberDto savedMember = memberService.storeMember(memberUpsertDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable Long memberId){
        try {
            MemberDto member = memberService.getById(memberId);
            return member != null ? ResponseEntity.status(HttpStatus.OK).body(member)
                    :ResponseEntity.status(HttpStatus.NOT_FOUND).body("Miembro no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PatchMapping("update/{memberId}")
    public ResponseEntity<?> update(@PathVariable Long memberId, @RequestBody MemberUpsertDto memberUpsertDto){
        try {
            MemberDto updatedFamilyMember = memberService.updateMember(memberId, memberUpsertDto);
            return updatedFamilyMember != null ? ResponseEntity.status(HttpStatus.OK).body(updatedFamilyMember)
                    :ResponseEntity.status(HttpStatus.NOT_FOUND).body("Miembro no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @DeleteMapping("delete/{memberId}")
    public ResponseEntity<?> delete(@PathVariable Long memberId){
        try {
            boolean isDeleted = memberService.deleteMember(memberId);
            return isDeleted ? ResponseEntity.status(HttpStatus.OK).body("Miembro eliminado")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Miembro no encontrado");

        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

}
