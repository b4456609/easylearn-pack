package soselab.easylearn.client;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import soselab.easylearn.service.PackService;
import soselab.easylearn.service.PackServiceImp;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ActiveProfiles("test")
public class UserClientTest {
    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("easylearn-user", "localhost", 8085, this);

    @Autowired
    private UserClient client;

    @Test
    @PactVerification
    public void getUserPacks() throws Exception {
        client.getUserPacks("Tom's id");
    }

    @Pact(consumer = "easylearn-pack")
    public PactFragment createFragment(PactDslWithProvider builder) {
        Object[] packServices = new PackService[2];
        packServices[0] = new PackServiceImp();

        return builder
                .uponReceiving("get user all pacts' id")
                .path("/pack")
                .headers("user-id", "Tom's id")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("[\"packid\", \"testPackId\"]", "application/json")
                .toFragment();


    }

}