package soselab.easylearn.service;

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
import soselab.easylearn.model.Version;
import soselab.easylearn.repository.PackRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyCollection;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PackServiceImpTest {


    @MockBean
    private PackRepository packRepository;
    @MockBean
    private UserClient userClient;
    @Autowired
    PackService packService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getUserPacks_getUserNull_emptyCollections() throws Exception {
        given(this.userClient.getUserPacks(anyString()))
                .willReturn(null);
        List<Pack> result = packService.getUserPacks("id");
        assertThat(result).isEmpty();
    }

    @Test
    public void getUserPacks_getUserPacks_packsArray() throws Exception {
        Pack pack = new Pack("name","id", "description",123, true, "userid",
                "creatorUserName", "coverFilename", new ArrayList<>(), 0);
        Pack pack1 = new Pack("name1","id1", "description",123, true, "userid",
                "creatorUserName", "coverFilename", new ArrayList<>(), 0);
        Iterable<Pack> packs = Arrays.asList(pack, pack1);
        given(this.packRepository.findAll(anyCollection()))
                .willReturn(packs);
        List<Pack> result = packService.getUserPacks("id");
        assertThat(result).contains(pack);
        assertThat(result).contains(pack1);
    }

    @Test
    public void addPack_AddPack2DB() throws Exception {
        Pack pack = new Pack();
        packService.addPack(pack);
        verify(packRepository).save(pack);
    }

    @Test
    public void addVersion_AddVersionToPack_SaveDB() throws Exception {
        Pack pack = new Pack("name","id", "description",123, true, "userid",
                "creatorUserName", "coverFilename", new ArrayList<>(), 0);
        Version version = new Version();
        given(packRepository.findOne("id"))
                .willReturn(pack);
        packService.addVersion("id", version);


        verify(packRepository).save(pack);
    }

    @Test
    public void updateVersion_updateVersion_SaveDB() throws Exception {
        Version version = new Version("ida", "content", 5687, false, "String creatorUserId",
                "asdf", Collections.EMPTY_SET, 1);
        Pack pack = new Pack("name","id", "description",123, true, "userid",
                "creatorUserName", "coverFilename", Collections.singletonList(version), 0);

        Version updateVersion = new Version("ida", "content12", 5687, false, "String creatorUserId",
                "asdf", Collections.EMPTY_SET, 1);

        given(packRepository.findOne("id"))
                .willReturn(pack);
        packService.updateVersion("id", version);

        verify(packRepository).save(pack);

    }

}