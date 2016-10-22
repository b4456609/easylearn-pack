package soselab.easylearn.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by bernie on 2016/9/11.
 */
@FeignClient("easylearn-user")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}/pack")
    List<String> getUserPacks(@PathVariable("storeId") String userId);
}
