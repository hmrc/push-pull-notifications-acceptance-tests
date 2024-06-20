package helpers.apis;

import net.serenitybdd.annotations.Step;
import apis.common.CommonApi;

import static io.restassured.http.ContentType.JSON;

public class ContentTypeHeaderHelper extends CommonApi {

    @Step
    public void withJsonContentTypeHeader() {
        builder().withJsonContentTypeHeader();
    }

    @Step
    public void withXmlContentTypeHeader() {
        builder().withXmlContentTypeHeader();
    }

    @Step
    public void withNoContentTypeHeader() {
        //Do not include any Content Type Headers

        //TODO - below solution included if not including a content type header starts to fail in the future
        //builder().withContentType(null);
    }

    @Step
    public void withInvalidContentTypeHeader() {
        builder().withContentType(JSON.getContentTypeStrings()[1]);
    }
}
