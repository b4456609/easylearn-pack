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
import soselab.easylearn.model.Pack;
import soselab.easylearn.repository.PackRepository;

import java.util.ArrayList;
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
        List<String> userPacks = userClient.getUserPacks(userId);
        if (userPacks == null) return null;
        Iterable<Pack> packs = packRepository.findAll(userPacks);
        List<Pack> pack = new ArrayList<>();
        packs.forEach(pack::add);
        return pack;
    }

}
