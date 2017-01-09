package soselab.easylearn.MCA.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import soselab.easylearn.MCA.output.model.MDP;
import soselab.easylearn.MCA.output.model.MDPBuilder;

public class MDPWriter {
    public void write(MDP mdp){
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        try {
            String jsonInString = mapper.writeValueAsString(mdp);
            System.out.println(jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
