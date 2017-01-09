@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ActiveProfiles("test")
public class UserClientTest {
    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("${provider}", "localhost", 8085, this);

    @Autowired
    private ${clientClass} client;

    @Test
    @PactVerification
    public void getUserPacks() throws Exception {
        // service call
        client.${methodName}();
    }

    @Pact(consumer = "${consumer}")
    public PactFragment createFragment(PactDslWithProvider builder) {

        // implement the response body message
        String response = "";

        return builder
                .uponReceiving("some description")
                .path("${path}")
                .method("${httpMethod}")
                .willRespondWith()
                .status(200)
                .body(response)
                .toFragment();
    }
}