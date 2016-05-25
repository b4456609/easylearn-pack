/**
 *
 */
package ntou.bernie.easylearn;

import com.fasterxml.jackson.databind.DeserializationFeature;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import ntou.bernie.easylearn.db.MorphiaService;
import ntou.bernie.easylearn.health.DatabaseHealthCheck;
import ntou.bernie.easylearn.pack.client.ConsulClient;
import ntou.bernie.easylearn.pack.client.PackNoteClient;
import ntou.bernie.easylearn.pack.client.PackUserClient;
import ntou.bernie.easylearn.pack.core.Pack;
import ntou.bernie.easylearn.pack.db.PackDAOImp;
import ntou.bernie.easylearn.pack.resource.PackResource;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.client.Client;

import java.util.EnumSet;

/**
 * @author bernie
 */
public class EasylearnPackAPP extends Application<EasylearnPackAPPConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EasylearnPackAPP.class);

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new EasylearnPackAPP().run(args);

    }

    @Override
    public void run(EasylearnPackAPPConfiguration configuration, Environment environment) throws Exception {
        environment.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");


        // mongodb driver
        MorphiaService morphia = new MorphiaService(configuration.getDatabaseConfiguration());


        final Client client = new JerseyClientBuilder().build();
        final Client client1 = new JerseyClientBuilder().build();

        ConsulClient consulClient = new ConsulClient();
        consulClient.getServiceHost(configuration.getHost());
        String host = consulClient.getHost();
        String userhost = "http://" + host + ":" + configuration.getUserServicePort() + "/";
        String notehost = "http://" + host + ":" + configuration.getNoteServicePort() + "/";

        LOGGER.info(host);
        LOGGER.info(userhost);
        LOGGER.info(notehost);

        PackNoteClient noteClient = new PackNoteClient(client, notehost);
        PackUserClient userClient = new PackUserClient(client1, userhost);

        PackResource packResource = new PackResource(new PackDAOImp(Pack.class, morphia.getDatastore()), userClient, noteClient);
        environment.jersey().register(packResource);

        // database health check
        environment.healthChecks().register("database",
                new DatabaseHealthCheck(configuration.getDatabaseConfiguration()));

    }

}
