package ntou.bernie.easylearn.pack.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import javax.ws.rs.client.Client;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by bernie on 2016/2/26.
 */
public class PackNoteClient {

    private final ObjectMapper objectMapper;
    private final Client client;
    private final String hostname;

    public PackNoteClient(Client client, String hostname) {
        this.client = client;
        this.hostname = hostname;
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }

    public JsonNode getNoteByVersionId(String versionId) throws IOException {
        Response response = client.target(hostname).path("note/").path(versionId).request().get();
        String json = response.readEntity(String.class);
        return objectMapper.readTree(json);
    }
}
