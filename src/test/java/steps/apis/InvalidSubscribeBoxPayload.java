package steps.apis;

public class InvalidSubscribeBoxPayload {
    private String invalidSubscriberType;
    private String invalidCallBackUrl;


    // To allow Jackson Json deserialization to work we need a default constructor.
    private InvalidSubscribeBoxPayload() {
    }

    public InvalidSubscribeBoxPayload(final String subscriberType, final String callBackUrl) {
        this.invalidSubscriberType = subscriberType;
        this.invalidCallBackUrl = callBackUrl;
    }

    public String getInvalidSubscriberType() {
        return invalidSubscriberType;
    }

    public String getInvalidCallBackUrl() {
        return invalidCallBackUrl;
    }

    @Override
    public String toString() {
        return "SubscribeTopicPayload{" +
                ", invalidSubscriberType='" + invalidSubscriberType + '\'' +
                ", invalidCallBackUrl='" + invalidCallBackUrl + '\'' +
                '}';
    }
}
