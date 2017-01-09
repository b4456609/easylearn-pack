package soselab.easylearn.MCA.parser;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import soselab.easylearn.MCA.parser.model.ClientInfo;

import java.lang.reflect.Method;
import java.util.*;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

public class ClientParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientParser.class);

    private final Reflections reflections;

    public ClientParser(Reflections reflections) {
        this.reflections = reflections;
    }

    public List<ClientInfo> getClient(){
        System.out.println(reflections.getTypesAnnotatedWith(FeignClient.class));
        ArrayList<Method> methods = new ArrayList<>();
        List<ClientInfo> collect = new ArrayList<>();

        for(Class aClass: reflections.getTypesAnnotatedWith(FeignClient.class)){
            FeignClient mergedAnnotation = findMergedAnnotation(aClass, FeignClient.class);
            String target = mergedAnnotation.name();
            for(Method method: aClass.getDeclaredMethods()){
                RequestMapping methodMapping = findMergedAnnotation(method, RequestMapping.class);
                methods.add(method);
                collect.add(new ClientInfo(method, methodMapping.value()[0], methodMapping.method()[0].toString(), target));
            }
        }

        return collect;
    }
}
