package soselab.easylearn.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("easylearn-user")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/pack")
    List<String> getUserPacks(@RequestHeader("user-id") String userId);
}
