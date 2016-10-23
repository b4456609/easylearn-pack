package soselab.easylearn.service;

import soselab.easylearn.model.Pack;
import soselab.easylearn.model.Version;

import java.util.List;

/**
 * Created by bernie on 2016/9/11.
 */
public interface PackService {
    List<Pack> getUserPacks(String userId);

    void addPack(Pack pack);

    void addVersion(String PackId, Version version);

//    void updateVersion(String PackId, Version version);
}
