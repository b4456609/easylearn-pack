package soselab.easylearn.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import soselab.easylearn.model.Pack;

/**
 * Created by bernie on 2016/9/11.
 */
public interface PackRepository extends MongoRepository<Pack, String> {

}
