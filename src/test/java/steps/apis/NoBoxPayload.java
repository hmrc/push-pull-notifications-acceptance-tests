package steps.apis;

public class NoBoxPayload {
    private String noClientId;
    private String noBoxName;

    // To allow Jackson Json deserialization to work we need a default constructor.
    private NoBoxPayload() {
    }

    public NoBoxPayload(final String clientId, final String boxName) {
        this.noClientId = clientId;
        this.noBoxName = boxName;
    }

    public String getNoClientId() {
        return noClientId;
    }

    public String getInvalidBoxName() {
        return noBoxName;
    }

    @Override
    public String toString() {
        return "NotificationsBoxPayload{" +
                "='" + noClientId + '\'' +
                ", ='" + noBoxName + '\'' +
                '}';
    }
}
