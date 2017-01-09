package soselab.easylearn.MCA;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import soselab.easylearn.MCA.parser.EndpointParser;
import soselab.easylearn.MCA.parser.model.ServiceEndpoint;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProjectReaderTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void execute() throws Exception {

        String body = restTemplate.getForEntity("/mappings", String.class).getBody();
        ProjectReader projectReader = new ProjectReader("easylearn-pack", body);
        projectReader.execute();
    }


}