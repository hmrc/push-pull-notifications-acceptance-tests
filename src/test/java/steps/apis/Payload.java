package steps.apis;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

public class Payload {
    private String method;
    private String uri;
    private String resourceDetails;

    public Payload(String method, String path, String details) {
        this.method = method;
        this.uri = path;
        this.resourceDetails = details;
    }

    // To allow Jackson Json deserialization to work we need a default constructor.
    private Payload() {
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getResourceDetails() {
        return resourceDetails;
    }

    public void setResourceDetails(String resourceDetails) {
        this.resourceDetails = resourceDetails;
    }


    public static Matcher<Payload> hasMethodFeatureMatcher(String in) {
        return new FeatureMatcher<Payload, String>(equalTo(in), "method", "method") {
            @Override
            protected String featureValueOf(final Payload actual) {
                return actual.getMethod();
            }
        };
    }

    public static Matcher<Payload> hasUriFeatureMatcher(String in) {
        return new FeatureMatcher<Payload, String>(equalTo(in), "uri", "uri") {
            @Override
            protected String featureValueOf(final Payload actual) {
                return actual.getUri();
            }
        };
    }

    public static Matcher<Payload> hasResourceDetailsFeatureMatcher(String in) {
        return new FeatureMatcher<Payload, String>(equalTo(in), "resource details", "resource details") {
            @Override
            protected String featureValueOf(final Payload actual) {
                return actual.getResourceDetails();
            }
        };
    }
}