package JeysonAmadoA.FamilyMoney.Factories;

import JeysonAmadoA.FamilyMoney.Entities.BaseEntity;
import com.github.javafaker.Faker;

public abstract class BaseFactory {

    protected final Faker faker;

    protected BaseFactory() {
        this.faker = new Faker();
    }

    public abstract BaseEntity create();
}
