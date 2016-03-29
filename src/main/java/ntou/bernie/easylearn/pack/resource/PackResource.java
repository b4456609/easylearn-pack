package ntou.bernie.easylearn.pack.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ntou.bernie.easylearn.pack.client.PackNoteClient;
import ntou.bernie.easylearn.pack.client.PackUserClient;
import ntou.bernie.easylearn.pack.core.CustomVersionDeserializer;
import ntou.bernie.easylearn.pack.core.Pack;
import ntou.bernie.easylearn.pack.core.Version;
import ntou.bernie.easylearn.pack.db.PackDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Path("/pack")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PackResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PackResource.class);
    private final PackDAO packDAO;
    private final ObjectMapper objectMapper;
    private final PackUserClient packUserClient;
    private final PackNoteClient packNoteClient;
    @Context
    UriInfo uriInfo;
    @Context
    private ResourceContext rc;

    /**
     * @param packDAO
     */
    public PackResource(PackDAO packDAO) {
        this.packDAO = packDAO;
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        packUserClient = new PackUserClient();
        packNoteClient = new PackNoteClient();

    }

    public PackResource(PackDAO packDAO, PackUserClient packUserClient, PackNoteClient packNoteClient) {
        this.packDAO = packDAO;
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        this.packUserClient = packUserClient;
        this.packNoteClient = packNoteClient;
    }

    @GET
    @Path("/user/{userId}")
    @Timed
    public Response getUserPacks(@PathParam("userId") String userId) {
        if (userId == null)
            throw new WebApplicationException(400);

        LOGGER.debug(userId);
        List<String> userPacks = packUserClient.getUserPacks(userId, rc);

        LOGGER.debug(userPacks.toString());

        try {
            // query from db
            List<Pack> packs = packDAO.getPacksById(userPacks);
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (Pack pack : packs) {
                //read pack to json node
                final ObjectNode packNode = objectMapper.valueToTree(pack);
                //get version array node
                final ArrayNode versionsNode = (ArrayNode) packNode.get("version");
                LOGGER.debug("versionsNode " + versionsNode.toString());
                //iterate each version to get note
                versionsNode.forEach(versionNode -> {
                    try {
                        String versionId = versionNode.get("id").asText();
                        JsonNode noteJson = packNoteClient.getNoteByVersionId(versionId, rc);
                        LOGGER.debug("pack's notes " + noteJson.toString());
                        ((ObjectNode) versionNode).set("note", noteJson);

                        //add bookmark
                        ((ObjectNode) versionNode).putArray("bookmark");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                arrayNode.add(packNode);
            }
            LOGGER.debug(arrayNode.toString());
            String json = objectMapper.writeValueAsString(arrayNode);
            return Response.ok(json).build();
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        return Response.serverError().build();
    }

    @POST
    @Path("/sync")
    @Timed
    public Response syncPacks(String syncJson) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Version.class, new CustomVersionDeserializer());
        objectMapper.registerModule(module);
        try {

            // map to comment object
            List<Pack> packs = objectMapper.readValue(syncJson, new TypeReference<List<Pack>>() {
            });

            // pack json validation
            for (Pack pack : packs) {
                packValidation(pack);
                // sync pack
                packDAO.sync(pack);
            }

            // build response
            return Response.ok().build();
        } catch (IOException e) {
            LOGGER.info("json pharse problem " + e);
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /*@GET
    @Path("/{packId}")
    @Timed
    public Pack getPack(@PathParam("packId") String packId) {
        // query from db
        Pack pack = packDAO.getPackById(packId);
        if (pack == null)
            throw new WebApplicationException(404);
        return pack;
    }

    @GET
    @Path("/{packId}/version")
    @Timed
    public List<Version> getPackVersion(@PathParam("packId") String packId) {
        // query from db
        Pack pack = packDAO.getPackById(packId);
        if (pack == null)
            throw new WebApplicationException(404);
        return pack.getVersion();
    }
*/
    /*@POST
    @Timed
    public Response addPack(String packJson) {
        try {
            // map to comment object
            Pack pack = objectMapper.readValue(packJson, Pack.class);

            // pack json validation
            packValidation(pack);

            // save to db
            packDAO.save(pack);

            // build response
            URI userUri = uriInfo.getAbsolutePathBuilder().path(pack.getId()).build();
            return Response.created(userUri).build();
        } catch (IOException e) {
            LOGGER.info("json pharse problem", e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }*/


    /*@POST
    @Path("/sync/single")
    @Timed
    public Response syncPack(String packJson) {
        try {
            // map to comment object
            Pack pack = objectMapper.readValue(packJson, Pack.class);

            // pack json validation
            packValidation(pack);

            // sync pack
            packDAO.sync(pack);

            // build response
            URI userUri = uriInfo.getAbsolutePathBuilder().path(pack.getId()).build();
            return Response.created(userUri).build();
        } catch (IOException e) {
            LOGGER.info("json pharse problem" + e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }*/

    private void packValidation(Pack pack) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        //json validation
        Set<ConstraintViolation<Pack>> constraintViolations = validator.validate(pack);
        for (ConstraintViolation<Pack> constraintViolation : constraintViolations) {
            LOGGER.warn(constraintViolation.toString());
        }
        if (constraintViolations.size() > 0) {
            //json validation fail
            LOGGER.warn("json validation fail");
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}
