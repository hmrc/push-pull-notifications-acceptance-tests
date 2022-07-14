package steps.apis;

public class ValidSubscribeBoxPayload {
    private String clientId;
    private String callBackUrl;


    // To allow Jackson Json deserialization to work we need a default constructor.
    private ValidSubscribeBoxPayload() {
    }

    public ValidSubscribeBoxPayload(final String clientId, final String callBackUrl) {
        this.clientId = clientId;
        this.callBackUrl = callBackUrl;
    }

    public String getSubscriberType() {
        return clientId;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    @Override
    public String toString() {
        return "SubscribeTopicPayload{" +
                ", clientId='" + clientId + '\'' +
                ", callBackUrl='" + callBackUrl + '\'' +
                '}';
    }
}
