package soselab.easylearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soselab.easylearn.exception.UserValidationFailException;
import soselab.easylearn.model.Pack;
import soselab.easylearn.model.Version;
import soselab.easylearn.model.dto.UpdateVersionDTO;
import soselab.easylearn.service.PackService;

import java.util.List;

/**
 * Created by bernie on 2016/9/11.
 */
@RestController
public class PackController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PackController.class);

    @Autowired
    PackService packService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Pack> getUserPacks(@RequestHeader("user-id") String userId) {
        LOGGER.info("getUserPacks");
        LOGGER.info(userId);
        return packService.getUserPacks(userId);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public void addPack(@RequestHeader("user-id") String userId, @RequestBody Pack pack) {
        LOGGER.info("addPack");
        LOGGER.info(userId);
        LOGGER.info(pack.toString());
        //auth the pack create
        if (!pack.getCreatorUserId().equals(userId))
            throw new UserValidationFailException();
        packService.addPack(pack);
    }

    @RequestMapping(path = "/{packId}/version", method = RequestMethod.POST)
    public void addVersion(@RequestHeader("user-id") String userId,
                           @PathVariable String packId, @RequestBody Version version) {

        //auth the pack create
        if (!version.getCreatorUserId().equals(userId))
            throw new UserValidationFailException();
        packService.addVersion(packId, version);
    }

    @RequestMapping(path = "/version", method = RequestMethod.PUT)
    public void updateVersion(@RequestBody UpdateVersionDTO updateVersionDTO) {
        packService.updateVersion(updateVersionDTO.getPackId(), updateVersionDTO.getVersionId(), updateVersionDTO.getContent());
    }
}
