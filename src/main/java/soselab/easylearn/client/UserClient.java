package soselab.easylearn.client;

import feign.Headers;
import feign.Param;
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
    @RequestMapping(method = RequestMethod.GET, value = "/pack")
    @Headers("user-id: {user-id}")
    List<String> getUserPacks(@Param("user-id") String userId);
}
