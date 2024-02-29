package JeysonAmadoA.FamilyMoney.Exceptions.Budgets;

public class RegisterBudgetException extends Exception {

    private static final String MESSAGE  = "Error al registrar presupuesto.";

    public RegisterBudgetException(String error) {
        super(MESSAGE + " " + error);
    }

    public RegisterBudgetException() {
        super(MESSAGE);
    }
}
