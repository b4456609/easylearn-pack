package soselab.easylearn.MCA;

import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import soselab.easylearn.MCA.parser.model.ClientCaller;
import soselab.easylearn.MCA.parser.model.ClientInfo;
import soselab.easylearn.MCA.parser.ClientEndpointParser;
import soselab.easylearn.MCA.parser.ClientParser;
import soselab.easylearn.PackApplication;

import java.net.URL;
import java.util.List;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

/**
 * Created by bernie on 1/6/17.
 */

public class SourceAnalyzer {


    private static final Logger LOGGER = LoggerFactory.getLogger(SourceAnalyzer.class);
    private final URL resource;
    Reflections reflections;


    public SourceAnalyzer() {
        this.resource = PackApplication.class.getProtectionDomain().getCodeSource().getLocation();
        reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(this.resource)
                .filterInputsBy(new FilterBuilder().exclude("test"))
                .setScanners(new SubTypesScanner(false), new TypeAnnotationsScanner(), new FieldAnnotationsScanner(),
                        new MethodAnnotationsScanner(), new MethodParameterScanner(), new MethodParameterNamesScanner(),
                        new MemberUsageScanner()));
    }

    public List<ClientCaller> getClient() {
        ClientParser clientParser = new ClientParser(reflections);
        ClientEndpointParser clientEndpointParser = new ClientEndpointParser(reflections);

        List<ClientInfo> client = clientParser.getClient();
        List<ClientCaller> info = clientEndpointParser.getInfo(client);
        System.out.println(client);
        System.out.println(info);
        return info;
    }

}
