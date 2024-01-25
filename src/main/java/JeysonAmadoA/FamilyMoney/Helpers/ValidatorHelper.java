package JeysonAmadoA.FamilyMoney.Helpers;

import JeysonAmadoA.FamilyMoney.Exceptions.General.DataIncompleteException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ValidatorHelper {

    public static <D> boolean validateNotNulls(D object, String[] excludedFields) throws DataIncompleteException {
        if (object == null) {
            return false;
        }

        Class<?> objectClass = object.getClass();
        Field[] campos = objectClass.getDeclaredFields();
        Set<String> excludedFieldsSet = new HashSet<>(Arrays.asList(excludedFields));

        for (Field campo : campos) {
            campo.setAccessible(true);

            try {
                if (!excludedFieldsSet.contains(campo.getName())) {
                    Object valor = campo.get(object);
                    if (valor == null) {
                        throw new Exception("El campo '" + campo.getName() + "' no contiene información.");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new DataIncompleteException(e.getMessage());
            }
        }
        return true;
    }
}
