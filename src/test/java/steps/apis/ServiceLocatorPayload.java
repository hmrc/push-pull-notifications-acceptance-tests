package steps.apis;

import static java.lang.String.format;

public class ServiceLocatorPayload {
    private String serviceName;
    private String serviceUrl;

    // To allow Jackson Json deserialization to work we need a default constructor.
    private ServiceLocatorPayload() {
    }

    public ServiceLocatorPayload(final String serviceName) {
        this.serviceName = serviceName;
        this.serviceUrl = format("https://%s.protected.mdtp", serviceName);
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    @Override
    public String toString() {
        return "ServiceLocatorPayload{" +
                "serviceName='" + serviceName + '\'' +
                ", serviceUrl='" + serviceUrl + '\'' +
                '}';
    }
}
