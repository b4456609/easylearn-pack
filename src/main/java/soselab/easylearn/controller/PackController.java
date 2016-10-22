package soselab.easylearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import soselab.easylearn.exception.UserValidationFailException;
import soselab.easylearn.model.Pack;
import soselab.easylearn.model.Version;
import soselab.easylearn.service.PackService;

import javax.ws.rs.HeaderParam;
import java.util.List;

/**
 * Created by bernie on 2016/9/11.
 */
@RestController
public class PackController {

    @Autowired
    PackService packService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Pack> getUserPacks(@HeaderParam("userId") String userId) {
        return packService.getUserPacks(userId);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public void addPack(@HeaderParam("userId") String userId, Pack pack) {
        //auth the pack create
        if (!pack.getCreatorUserId().equals(userId))
            throw new UserValidationFailException();
        packService.addPack(pack);
    }

    @RequestMapping(path = "/{packId}/version", method = RequestMethod.POST)
    public void addVersion(@HeaderParam("userId") String userId, @PathVariable String packId, Version version) {
        //auth the pack create
        if (!version.getCreatorUserId().equals(userId))
            throw new UserValidationFailException();
        packService.addVersion(version);
    }

    @RequestMapping(path = "/{packId}/version", method = RequestMethod.PUT)
    public void updateVersion(@PathVariable String packId, Version version) {
        packService.updateVersion(version);
    }
}
