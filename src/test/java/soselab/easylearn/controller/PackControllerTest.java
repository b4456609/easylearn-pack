package soselab.easylearn.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import soselab.easylearn.client.UserClient;
import soselab.easylearn.model.Pack;
import soselab.easylearn.model.PackBuilder;
import soselab.easylearn.model.Version;
import soselab.easylearn.model.VersionBuilder;
import soselab.easylearn.repository.PackRepository;

import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by bernie on 2016/10/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PackControllerTest {

    @MockBean
    UserClient userClient;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PackRepository packRepository;

    @Before
    public void setUp() {
        packRepository.deleteAll();
        Pack pack = new PackBuilder()
                .setId("packIdpackId")
                .setName("packName")
                .createPack();
        Pack pack1 = new PackBuilder()
                .setId("packId")
                .setName("pack")
                .createPack();
        packRepository.save(pack);
        packRepository.save(pack1);
    }


    @Test
    public void getUserPacks_getUsersPack_ListOfPack() throws Exception {
        when(userClient.getUserPacks("id"))
                .thenReturn(Arrays.asList("packIdpackId", "packId"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("user-id", "id");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = this.restTemplate.exchange("/", HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("packIdpackId");
        assertThat(response.getBody()).contains("packName");
        assertThat(response.getBody()).contains("packId");
        assertThat(response.getBody()).contains("pack");
    }

    @Test
    public void getUserPacks_getUsersPackWhichIsNotExist_EmptyList() throws Exception {
        when(userClient.getUserPacks("id"))
                .thenReturn(Arrays.asList("packIdpackIda", "packIda"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("user-id", "id");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = this.restTemplate.exchange("/", HttpMethod.GET, entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).doesNotContain("packIdpackId");
        assertThat(response.getBody()).doesNotContain("packName");
        assertThat(response.getBody()).doesNotContain("packId");
        assertThat(response.getBody()).doesNotContain("pack");
        System.out.println(response.getBody());
    }

    @Test
    public void addPack_AddUserPack_addPackToDB() throws Exception {
        Pack pack1 = new PackBuilder()
                .setId("packIda")
                .setName("pack")
                .setCreatorUserId("id")
                .createPack();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("user-id", "id");

        String json = objectMapper.writeValueAsString(pack1);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = this.restTemplate
                .exchange("/", HttpMethod.POST, entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(packRepository.count()).isEqualTo(3);
    }


    @Test
    public void addPack_AddUserPackIsNotAuthor_badRequest() throws Exception {
        Pack pack1 = new PackBuilder()
                .setId("packIda")
                .setName("pack")
                .setCreatorUserId("ida")
                .createPack();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("user-id", "id");

        String json = objectMapper.writeValueAsString(pack1);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = this.restTemplate
                .exchange("/", HttpMethod.POST, entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void addVersion_AddUserPackIsNotAuthor_badRequest() throws Exception {
        Version version = new VersionBuilder()
                .setId("versionId")
                .setCreatorUserId("ida")
                .createVersion();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("user-id", "id");

        String json = objectMapper.writeValueAsString(version);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = this.restTemplate
                .exchange("/packId/version", HttpMethod.POST, entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


    @Test
    public void addVersion_AddUserVersion_addToDB() throws Exception {
        Version version = new VersionBuilder()
                .setId("versionId")
                .setCreatorUserId("id")
                .createVersion();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("user-id", "id");

        String json = objectMapper.writeValueAsString(version);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = this.restTemplate
                .exchange("/packId/version", HttpMethod.POST, entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(packRepository.findOne("packId").getVersion().size()).isEqualTo(1);
    }

    @Test
    public void addVersion_AddUserVersion_PackIdNotFound() throws Exception {
        Version version = new VersionBuilder()
                .setId("versionId")
                .setCreatorUserId("id")
                .createVersion();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("user-id", "id");

        String json = objectMapper.writeValueAsString(version);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<String> response = this.restTemplate
                .exchange("/packIdasdf/version", HttpMethod.POST, entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}