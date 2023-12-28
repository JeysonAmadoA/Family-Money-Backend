package JeysonAmadoA.FamilyMoney.Exceptions.General;

public class StoreException extends Exception {

    private static final String MESSAGE  = "Error al almacenar.";

    public StoreException(String error) {
        super(MESSAGE + " " + error);
    }

    public StoreException() {
        super(MESSAGE);
    }
}