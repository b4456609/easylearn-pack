package soselab.easylearn.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soselab.easylearn.client.NoteClient;
import soselab.easylearn.client.UserClient;
import soselab.easylearn.exception.PackNotFoundException;
import soselab.easylearn.model.Pack;
import soselab.easylearn.model.Version;
import soselab.easylearn.repository.PackRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bernie on 2016/9/11.
 */
@Service
public class PackServiceImp implements PackService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PackServiceImp.class);
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    @Autowired
    private PackRepository packRepository;
    @Autowired
    private UserClient userClient;
    @Autowired
    private NoteClient noteClient;

    @Override
    public List<Pack> getUserPacks(String userId) {
        LOGGER.info(userId);
        List<String> userPacks = userClient.getUserPacks(userId);
        LOGGER.info(userPacks.toString());
        if (userPacks == null) return Collections.emptyList();
        Iterable<Pack> packs = packRepository.findAll(userPacks);
        List<Pack> pack = new ArrayList<>();
        packs.forEach(pack::add);
        return pack;
    }

    @Override
    public void addPack(Pack pack) {
        packRepository.save(pack);
    }

    @Override
    public void addVersion(String packId, Version version) {
        Pack pack = packRepository.findOne(packId);
        if (pack == null) throw new PackNotFoundException();
        pack.getVersion().add(version);
        packRepository.save(pack);
    }

//    @Override
//    public void updateVersion(String packId, Version version) {
//        Pack pack = packRepository.findOne(packId);
//        if(pack == null) throw new PackNotFoundException();
//
//        Version dbVersion = pack.getVersion()
//                .stream()
//                .filter(v -> !v.getId().equals(version.getId()))
//                .findAny()
//                .orElse(null);
//
//        dbVersion.add(version);
//        pack.setVersion(dbVersion);
//        packRepository.save(pack);
//    }

}
