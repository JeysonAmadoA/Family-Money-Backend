package JeysonAmadoA.FamilyMoney.Utilities.Security;


import io.github.cdimascio.dotenv.Dotenv;

public class EnvironmentValues {

    private static EnvironmentValues instance;
    private final String SECRET_KEY;
    private final String CLIENT_HOST;

    private EnvironmentValues() {
        Dotenv dotenv = Dotenv.load();
        this.SECRET_KEY = dotenv.get("SECRET_KEY");
        this.CLIENT_HOST = dotenv.get("CLIENT_HOST");
    }

    public static synchronized EnvironmentValues getInstance() {
        if (instance == null) {
            instance = new EnvironmentValues();
        }
        return instance;
    }

    public String SECRET_KEY(){
        return SECRET_KEY;
    }

    public String CLIENT_HOST() { return CLIENT_HOST; }
}
