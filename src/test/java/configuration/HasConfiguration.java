package configuration;

public class HasConfiguration {

    protected static Configuration config = new Configuration();

    protected String baseApiUrl() {
        return config.baseApiUrl();
    }
}
