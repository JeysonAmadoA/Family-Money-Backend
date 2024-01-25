package JeysonAmadoA.FamilyMoney.Exceptions.General;

public class NotFoundException extends Exception {

    private static final String MESSAGE = "No se encontró elemento con el ID";

    public NotFoundException(String error) {
        super(MESSAGE + " " + error);
    }

    public NotFoundException() {
        super(MESSAGE);
    }
}
