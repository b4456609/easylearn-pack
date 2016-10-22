package soselab.easylearn.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import soselab.easylearn.client.UserClient;
import soselab.easylearn.model.Pack;
import soselab.easylearn.repository.PackRepository;
import soselab.easylearn.service.PackService;

import java.util.Arrays;
import java.util.Iterator;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyCollection;

/**
 * Created by bernie on 2016/10/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PackControllerTest {


    @Test
    public void getUserPacks() throws Exception {

    }

    @Test
    public void addPack() throws Exception {

    }

    @Test
    public void addVersion() throws Exception {

    }

    @Test
    public void updateVersion() throws Exception {

    }

}