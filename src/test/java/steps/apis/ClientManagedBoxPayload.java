package steps.apis;

public class ClientManagedBoxPayload {
    private String boxName;

    // To allow Jackson Json deserialization to work we need a default constructor.
    private ClientManagedBoxPayload() {
    }

    public ClientManagedBoxPayload(final String boxName) {
        this.boxName = boxName;
    }

    public String getBoxName() {
        return boxName;
    }

    @Override
    public String toString() {
        return "ClientManagedBoxPayload{" +
                ", boxName='" + boxName + '\'' +
                '}';
    }
}
