package JeysonAmadoA.FamilyMoney.Helpers;

import JeysonAmadoA.FamilyMoney.Exceptions.Members.RegisterMembersException;

public class MembersHelper {

    public static boolean verifyMembersIsComplete(int membersActualQuantity, int membersGroupQuantity) throws RegisterMembersException {

        if (membersActualQuantity == membersGroupQuantity) {
            throw new RegisterMembersException("Ya registró la máxima cantidad de miembros");
        } else {
            return true;
        }
    }
}
