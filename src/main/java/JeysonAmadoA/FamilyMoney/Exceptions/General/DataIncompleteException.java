package JeysonAmadoA.FamilyMoney.Exceptions.General;

public class DataIncompleteException extends Exception {

    private static final String MESSAGE  = "Datos incompletos para realizar el registro";

    public DataIncompleteException(String error) {
        super(MESSAGE + " " + error);
    }

    public DataIncompleteException() {
        super(MESSAGE);
    }
}