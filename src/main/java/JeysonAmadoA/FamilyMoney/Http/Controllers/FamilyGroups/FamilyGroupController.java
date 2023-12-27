package JeysonAmadoA.FamilyMoney.Http.Controllers.FamilyGroups;

import JeysonAmadoA.FamilyMoney.Dto.FamilyGroups.FamilyGroupDto;
import JeysonAmadoA.FamilyMoney.Dto.FamilyGroups.FamilyGroupUpsertDto;
import JeysonAmadoA.FamilyMoney.Interfaces.Services.FamilyGroups.FamilyGroupsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/family-group")
public class FamilyGroupController {

    private final FamilyGroupsServiceInterface familyGroupsService;

    @Autowired
    public FamilyGroupController(FamilyGroupsServiceInterface familyGroupsService) {
        this.familyGroupsService = familyGroupsService;
    }

    @PostMapping("/store")
    public ResponseEntity<?> store(@RequestBody FamilyGroupUpsertDto groupDto){
        try{
            FamilyGroupDto savedGroup = familyGroupsService.storeFamilyGroup(groupDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedGroup);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        try {
            List<FamilyGroupDto> familyGroups = familyGroupsService.getFamilyGroups();
            return ResponseEntity.status(HttpStatus.OK).body(familyGroups);
        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PatchMapping("update/{groupId}")
    public ResponseEntity<?> update(@PathVariable Long groupId, @RequestBody FamilyGroupUpsertDto familyGroupDto){
        try {
            FamilyGroupDto updatedFamilyGroup = familyGroupsService.updateFamilyGroup(groupId, familyGroupDto);
            if (updatedFamilyGroup != null)
                return ResponseEntity.status(HttpStatus.OK).body(updatedFamilyGroup);
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo no encontrado");
        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @DeleteMapping("delete/{groupId}")
    public ResponseEntity<?> delete(@PathVariable Long groupId){
        try {
            boolean isDeleted = familyGroupsService.deleteFamilyGroup(groupId);
            if (isDeleted)
                return ResponseEntity.status(HttpStatus.OK).body("Grupo eliminado");
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo no encontrado");
        } catch (Exception exception) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }


}
