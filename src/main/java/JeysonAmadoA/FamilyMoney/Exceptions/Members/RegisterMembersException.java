package JeysonAmadoA.FamilyMoney.Exceptions.Members;

public class RegisterMembersException extends Exception {

    private static final String MESSAGE  = "Error al registrar miembro.";

    public RegisterMembersException(String error) {
        super(MESSAGE + " " + error);
    }

    public RegisterMembersException() {
        super(MESSAGE);
    }
}
