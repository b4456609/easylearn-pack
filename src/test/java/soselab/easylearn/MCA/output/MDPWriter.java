package soselab.easylearn.MCA.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import soselab.easylearn.MCA.output.model.MDP;

import java.io.*;

public class MDPWriter {
    public void write(MDP mdp) {
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        try {
            String jsonInString = mapper.writeValueAsString(mdp);
            System.out.println(jsonInString);

            final File parent = new File("build/mca");
            if (!parent.mkdirs()) {
                System.err.println("Could not create parent directories ");
            }
            final File someFile = new File(parent, "mca.json");
            someFile.createNewFile();


            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("build/mca/mca.json"), "utf-8"))) {
                writer.write(jsonInString);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
