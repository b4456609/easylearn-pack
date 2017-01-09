package soselab.easylearn.MCA;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.reflections.util.ClasspathHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import soselab.easylearn.MCA.gen.TestingCodeGen;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProjectReaderTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void execute() throws Exception {
        String mappingsJson = restTemplate.getForEntity("/mappings", String.class).getBody();
        String swaggerJson = restTemplate.getForEntity("/v2/api-docs", String.class).getBody();
//        URL srcLocation = PackApplication.class.getProtectionDomain().getCodeSource().getLocation();
        Collection<URL> allLocation = ClasspathHelper.forPackage("soselab.easylearn");
//        System.out.println(allLocation);
        ProjectReader projectReader = new ProjectReader("easylearn-pack", mappingsJson, swaggerJson, allLocation);
        projectReader.execute();
    }

    @Test
    public void test() throws IOException {
        TestingCodeGen testingCodeGen = new TestingCodeGen();
        String s = testingCodeGen.genCode("easylearnp", "easylearnc", "getSomethings",
                "/path/id/name", "DELETE", "SomeClientClass");
        System.out.println(s);
    }

}