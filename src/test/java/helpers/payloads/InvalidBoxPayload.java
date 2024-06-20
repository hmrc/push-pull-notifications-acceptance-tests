package helpers.payloads;

public class InvalidBoxPayload {
    private String invalidClientId;
    private String invalidBoxName;

    // To allow Jackson Json deserialization to work we need a default constructor.
    private InvalidBoxPayload() {
    }

    public InvalidBoxPayload(final String clientId, final String boxName) {
        this.invalidClientId = clientId;
        this.invalidBoxName = boxName;
    }

    public String getInvalidClientId() {
        return invalidClientId;
    }

    public String getInvalidBoxName() {
        return invalidBoxName;
    }

    @Override
    public String toString() {
        return "NotificationsBoxPayload{" +
                "invalidClientId='" + invalidClientId + '\'' +
                ", invalidBoxName='" + invalidBoxName + '\'' +
                '}';
    }
}
