package JeysonAmadoA.FamilyMoney.Exceptions.Users;

public class RegisterUserException  extends Exception{

    private static final String MESSAGE  = "Error al crear usuario.";

    public RegisterUserException(String error) {
        super(MESSAGE + " " + error);
    }

    public RegisterUserException() {
        super(MESSAGE);
    }
}
