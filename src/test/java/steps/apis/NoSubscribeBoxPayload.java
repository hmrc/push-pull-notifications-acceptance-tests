package steps.apis;

public class NoSubscribeBoxPayload {
    private String noubscriberId;
    private String noSubscriberType;
    private String noCallBackUrl;


    // To allow Jackson Json deserialization to work we need a default constructor.
    private NoSubscribeBoxPayload() {
    }

    public NoSubscribeBoxPayload(final String subscriberId, final String subscriberType, final String callBackUrl) {
        this.noubscriberId = subscriberId;
        this.noSubscriberType = subscriberType;
        this.noCallBackUrl = callBackUrl;
    }


    public String getNoSubscriberId() {
        return noubscriberId;
    }

    public String getNoSubscriberType() {
        return noSubscriberType;
    }

    public String getNoCallBackUrl() {
        return noCallBackUrl;
    }

    @Override
    public String toString() {
        return "SubscribeTopicPayload{" +
                "='" + noubscriberId + '\'' +
                ", ='" + noSubscriberType + '\'' +
                ", ='" + noCallBackUrl + '\'' +
                '}';
    }
}
