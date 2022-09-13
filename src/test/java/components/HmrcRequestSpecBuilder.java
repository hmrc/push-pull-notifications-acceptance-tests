package components;

import configuration.HasConfiguration;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Wraps a RequestSpecBuilder in order to allow for defaulting without explicit Cucumber steps.
 */
public class HmrcRequestSpecBuilder extends HasConfiguration {

    private RequestSpecBuilder inner = new RequestSpecBuilder();
    private boolean needsDefaultHeader = true;
    private boolean needsProxy = config.isRunningOnCI();

    private String contentType;

    public RequestSpecification build() {
        applyDefaults();
        return inner.build();
    }

    private void applyDefaults() {
        if (needsDefaultHeader) {
            setAccept("application/vnd.hmrc.1.0+json");
        }

        if (needsProxy) {
            inner.setProxy(config.proxyPort());
        }
    }

    public HmrcRequestSpecBuilder withProxy() {
        needsProxy = true;
        return this;
    }

    public HmrcRequestSpecBuilder withNoProxy() {
        needsProxy = false;
        return this;
    }

    public HmrcRequestSpecBuilder setAccept(String mediaType) {
        inner = inner.setAccept(mediaType);
        needsDefaultHeader = false;
        return this;
    }

    public HmrcRequestSpecBuilder setNoAccept() {
        needsDefaultHeader = false;
        return this;
    }

    public HmrcRequestSpecBuilder withJsonContentTypeHeader() {
        inner = inner.setContentType(ContentType.JSON);
        return this;
    }

    public HmrcRequestSpecBuilder withXmlContentTypeHeader() {
        inner = inner.setContentType(ContentType.XML);
        return this;
    }

    @Step
    public void withContentType(final String contentType) {
        this.contentType = contentType;
    }

    public HmrcRequestSpecBuilder withNoContentTypeHeader(String s) {
        inner = inner.setBody(s);
        return this;
    }

    public HmrcRequestSpecBuilder setAuth(AuthenticationScheme authenticationScheme) {
        inner = inner.setAuth(authenticationScheme);
        return this;
    }

    public HmrcRequestSpecBuilder addHeader(String headerName, String headerValue) {
        inner = inner.addHeader(headerName, headerValue);
        return this;
    }

    public HmrcRequestSpecBuilder addHeaders(Map<String, String> headers) {
        inner = inner.addHeaders(headers);
        return this;
    }

    public HmrcRequestSpecBuilder withJsonBody(String s) {
        inner = inner.setBody(s).setContentType(ContentType.JSON);
        return this;
    }

    public HmrcRequestSpecBuilder withXmlBody(String s) {
        inner = inner.setBody(s).setContentType(ContentType.XML);
        return this;
    }

    public HmrcRequestSpecBuilder withXmlBodyFromFile(String path) {
        try {
            String s = new String(Files.readAllBytes(Paths.get(path)), UTF_8);
            return withXmlBody(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
