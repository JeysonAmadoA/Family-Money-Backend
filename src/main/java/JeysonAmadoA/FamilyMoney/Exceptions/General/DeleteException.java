package JeysonAmadoA.FamilyMoney.Exceptions.General;

public class DeleteException extends Exception {

    private static final String MESSAGE  = "Error al eliminar.";

    public DeleteException(String error) {
        super(MESSAGE + " " + error);
    }

    public DeleteException() {
        super(MESSAGE);
    }
}