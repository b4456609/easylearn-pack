package soselab.easylearn.MCA.parser;

import org.json.JSONObject;
import soselab.easylearn.MCA.parser.model.ServiceEndpoint;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bernie on 1/9/17.
 */
public class EndpointParser {

    private final String serviceName;

    public EndpointParser(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<ServiceEndpoint> getMappings(String mappingJson){
        System.out.println(mappingJson);
        JSONObject jsonObject = new JSONObject(mappingJson);
        List<ServiceEndpoint> pathMedthodPair = new ArrayList<>();
        Iterator keys = jsonObject.keys();
        while(keys.hasNext()){
            String key = (String) keys.next();
            JSONObject target = jsonObject.getJSONObject(key);
            if(target.has("method") && target.getString("method").contains("soselab.easylearn")){

                String path = getPath(key);
                String httpMethod = getHttpMethod(key);
                String id = String.format("%s endpoint %s %s", serviceName, path, httpMethod);

                pathMedthodPair.add(new ServiceEndpoint(id,path,httpMethod,target.getString("method")));
            }
        }
        return pathMedthodPair;
    }

    private String getPath(String input){

        Pattern pattern = Pattern.compile("\\[.*?\\]");
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        String group = matcher.group();
        String path = group.substring(1, group.length() - 1);
        System.out.println(path);
        return path;
    }


    private String getHttpMethod(String input){

        Pattern pattern = Pattern.compile("=\\[.*?\\]");
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        String group = matcher.group();
        String method = group.substring(2, group.length() - 1);
        System.out.println(method);
        return method;
    }
}
