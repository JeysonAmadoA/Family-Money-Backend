package JeysonAmadoA.FamilyMoney.Helpers;

import JeysonAmadoA.FamilyMoney.Exceptions.Budgets.RegisterBudgetException;

public class BudgetHelper {

    public static boolean verifyPercentage(float newPercentage, float totalPercentage) throws RegisterBudgetException {

        if (newPercentage + totalPercentage <= 100) {
            return true;
        } else {
            throw new RegisterBudgetException("El valor de porcentage excede del 100%");
        }
    }
}
