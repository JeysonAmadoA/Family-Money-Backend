package JeysonAmadoA.FamilyMoney.Exceptions.General;

public class UpdateException extends Exception {

    private static final String MESSAGE  = "Error al actualizar.";

    public UpdateException(String error) {
        super(MESSAGE + " " + error);
    }

    public UpdateException() {
        super(MESSAGE);
    }
}