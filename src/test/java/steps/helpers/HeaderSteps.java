package steps.helpers;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matcher;
import steps.apis.CommonApiSteps;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.nullValue;

public class HeaderSteps extends CommonApiSteps {

    private static final List<String> LIST_OF_UNWANTED_HEADERS = ImmutableList.of(
            "Akamai-Origin-Hop",
            "Server",
            "Cookie",
            "True-Client-IP",
            "True-Client-Port",
            "x-amz-request-id",
            "x-amz-id-2",
            "X-Akamai-CONFIG-LOG-DETAIL",
            "X-EdgeConnect-Session-Id",
            "X-Forwarded-For",
            "X-Forwarded-Host",
            "X-Forwarded-Proto",
            "X-Forwarded-Port",
            "X-Forwarded-Server",
            "X-Real-Ip",
            "X-Request-Id"
    );

    private static final String TRUE_CLIENT_IP_HEADER = "true-client-ip";

    private static final Matcher<?> isAbsent = is(nullValue());


    public void wwwAuthenticateHeaderIsReturned() {
        Matcher<String> wwwAuthenticate = is("Bearer realm=\"HMRC API Platform\"");
        response().header("Www-Authenticate", wwwAuthenticate);
    }

    public void wwwAuthenticateHeaderIsNotReturned() {
        response().header("Www-Authenticate", isAbsent);
    }
}
