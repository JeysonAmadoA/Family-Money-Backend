package JeysonAmadoA.FamilyMoney.Helpers;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class MapperHelper {

    public static void updateFieldIfNotNull(Long value, Consumer<Long> updateFunction) {
        if (value != null) {
            updateFunction.accept(value);
        }
    }

    public static void updateFieldIfNotNull(String value, Consumer<String> updateFunction) {
        if (value != null) {
            updateFunction.accept(value);
        }
    }

    public static void updateFieldIfNumberNotZero(int value, IntConsumer updateFunction) {
        if (value != 0) {
            updateFunction.accept(value);
        }
    }

    public static void updateFieldIfNumberNotZero(float value, Consumer<Float> updateFunction) {
        if (value != 0) {
            updateFunction.accept(value);
        }
    }
}
