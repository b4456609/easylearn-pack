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
import soselab.easylearn.model.PackBuilder;
import soselab.easylearn.model.Version;
import soselab.easylearn.model.VersionBuilder;
import soselab.easylearn.repository.PackRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

@SuppressWarnings("unchecked")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PackServiceImpTest {


    @Autowired
    PackService packService;
    @MockBean
    private PackRepository packRepository;
    @MockBean
    private UserClient userClient;

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
        Pack pack = new PackBuilder().setName("name").setId("id").setDescription("description").setCreateTime(123).setIsPublic(true).setCreatorUserId("userid").setCreatorUserName("creatorUserName").setCoverFilename("coverFilename").setVersion(new ArrayList<>()).setViewCount(0).createPack();
        Pack pack1 = new PackBuilder().setName("name1").setId("id1").setDescription("description").setCreateTime(123).setIsPublic(true).setCreatorUserId("userid").setCreatorUserName("creatorUserName").setCoverFilename("coverFilename").setVersion(new ArrayList<>()).setViewCount(0).createPack();
        Iterable<Pack> packs = Arrays.asList(pack, pack1);
        //noinspection unchecked
        given(this.packRepository.findAll(anyCollection()))
                .willReturn(packs);
        List<Pack> result = packService.getUserPacks("id");
        assertThat(result).contains(pack);
        assertThat(result).contains(pack1);
    }

    @Test
    public void addPack_AddPack2DB() throws Exception {
        Pack pack = new PackBuilder().createPack();
        packService.addPack(pack);
        verify(packRepository).save(pack);
    }

    @Test
    public void addVersion_AddVersionToPack_SaveDB() throws Exception {
        Pack pack = new PackBuilder().setName("name").setId("id").setDescription("description").setCreateTime(123).setIsPublic(true).setCreatorUserId("userid").setCreatorUserName("creatorUserName").setCoverFilename("coverFilename").setVersion(new ArrayList<>()).setViewCount(0).createPack();
        Version version = new VersionBuilder().createVersion();
        given(packRepository.findOne("id"))
                .willReturn(pack);
        packService.addVersion("id", version);


        verify(packRepository).save(pack);
    }

    @Test
    public void updateVersion_updateVersion_SaveDB() throws Exception {
        Version version = new VersionBuilder().setId("ida").setContent("content").setCreateTime(5687).setIsPublic(false).setCreatorUserId("String creatorUserId").setCreatorUserName("asdf").setFile(Collections.EMPTY_SET).setViewCount(1).createVersion();
        Pack pack = new PackBuilder().setName("name").setId("id").setDescription("description").setCreateTime(123).setIsPublic(true).setCreatorUserId("userid").setCreatorUserName("creatorUserName").setCoverFilename("coverFilename").setVersion(Collections.singletonList(version)).setViewCount(0).createPack();

        Version updateVersion = new VersionBuilder().setId("ida").setContent("content12").setCreateTime(5687).setIsPublic(false).setCreatorUserId("String creatorUserId").setCreatorUserName("asdf").setFile(Collections.EMPTY_SET).setViewCount(1).createVersion();

        given(packRepository.findOne("id"))
                .willReturn(pack);
        packService.updateVersion("id", version);

        verify(packRepository).save(pack);

    }

}