package soselab.easylearn.MCA;

import soselab.easylearn.MCA.output.MDPWriter;
import soselab.easylearn.MCA.output.model.MDP;
import soselab.easylearn.MCA.output.model.MDPBuilder;
import soselab.easylearn.MCA.output.model.ServiceCall;
import soselab.easylearn.MCA.parser.EndpointParser;
import soselab.easylearn.MCA.parser.model.ClientCaller;
import soselab.easylearn.MCA.parser.model.ClientInfo;
import soselab.easylearn.MCA.parser.model.ServiceEndpoint;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by bernie on 1/9/17.
 */
public class ProjectReader {
    private final String serviceName;
    private final SourceAnalyzer sourceAnalyzer;
    private final String mappingJson;
    private final EndpointParser endpointParser;

    public ProjectReader(String serviceName, String mappingJson) {
        this.serviceName = serviceName;
        this.sourceAnalyzer = new SourceAnalyzer();
        this.mappingJson = mappingJson;
        this.endpointParser = new EndpointParser("easyelarn-pack");
    }

    public void execute(){
        List<ClientCaller> client = sourceAnalyzer.getClient();
        List<ServiceCall> serviceCalls = client.stream().map(clientCaller -> {
            ClientInfo clientInfo = clientCaller.getClientInfo();
            String id = String.format("%s endpoint %s %s", serviceName, clientInfo.getPath(), clientInfo.getHttpMethod());
            return new ServiceCall(id, clientInfo.getPath(), clientInfo.getHttpMethod(), clientInfo.getTarget());
        }).collect(Collectors.toList());

        List<ServiceEndpoint> serviceEndpoints = endpointParser.getMappings(mappingJson);

        clientEndpointDep(client, serviceEndpoints);

        MDP mdp = new MDPBuilder()
                .setName(serviceName)
//                .setEndpoint(endpoints)
                .setServiceCall(serviceCalls)
                .createMDP();

        MDPWriter mdpWriter = new MDPWriter();
        mdpWriter.write(mdp);
    }

    private void clientEndpointDep(List<ClientCaller> client, List<ServiceEndpoint> serviceEndpoints){
        for(ClientCaller clientCaller: client){
            Set<Member> members = clientCaller.getMembers();
            members.forEach(member -> {
                serviceEndpoints.forEach(serviceEndpoint -> {
                    System.out.println(serviceEndpoint.getMethod());
                    System.out.println();
                    if(serviceEndpoint.getMethod().equals(((Method)member).toString())){
                        System.out.println("FOUND");
                    }
                });
            });

        }
    }
}
