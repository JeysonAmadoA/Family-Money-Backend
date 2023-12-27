package JeysonAmadoA.FamilyMoney.Exceptions.General;

public class GetException extends Exception {

    private static final String MESSAGE = "Error al recuperar.";

    public GetException(String error) {
        super(MESSAGE + " " + error);
    }

    public GetException() {
        super(MESSAGE);
    }
}