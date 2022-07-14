package steps.apis;

public class BoxPayload {
    private String clientId;
    private String boxName;

    // To allow Jackson Json deserialization to work we need a default constructor.
    private BoxPayload() {
    }

    public BoxPayload(final String clientId, final String boxName) {
        this.clientId = clientId;
        this.boxName = boxName;
    }

    public String getClientId() {
        return clientId;
    }

    public String getBoxName() {
        return boxName;
    }

    @Override
    public String toString() {
        return "NotificationsBoxPayload{" +
                "clientId='" + clientId + '\'' +
                ", boxName='" + boxName + '\'' +
                '}';
    }
}
