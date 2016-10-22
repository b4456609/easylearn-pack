package soselab.easylearn.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by bernie on 2016/9/11.
 */
@FeignClient("easylearn-note")
public interface NoteClient {
    @RequestMapping(method = RequestMethod.GET, value = "/note/{versionId}")
    String getNoteByVersionId(@PathVariable("versionId") String versionId);
}
