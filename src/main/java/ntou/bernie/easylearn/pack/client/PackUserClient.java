package ntou.bernie.easylearn.pack.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by bernie on 2016/2/26.
 */
public class PackUserClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(PackUserClient.class);

    private final ObjectMapper objectMapper;
    private final Client client;
    private final String hostname;

    public PackUserClient(Client client, String hostname) {
        this.hostname = hostname;
        this.client = client;
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }

    public List<String> getUserPacks(String userId) throws IOException {
        LOGGER.debug(userId);
        Response response = client.target(hostname).path("user/").path(userId).path("/pack").request().get();
        LOGGER.debug(response.toString());
        String json = response.readEntity(String.class);
        LOGGER.debug(json);
        return objectMapper.readValue(json, new TypeReference<List<String>>() { });
    }
}
