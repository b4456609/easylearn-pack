package soselab.easylearn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soselab.easylearn.model.Pack;
import soselab.easylearn.model.PackBuilder;
import soselab.easylearn.model.Version;
import soselab.easylearn.model.VersionBuilder;
import soselab.easylearn.repository.PackRepository;

import java.util.Arrays;

@Component
public class DatabaseTestData implements CommandLineRunner {

    @Value("${easylearn.isTest}")
    private boolean isTest;

    @Autowired
    private PackRepository packRepository;

    @Override
    public void run(String... args) throws Exception {
        if (isTest) {
            packRepository.deleteAll();
            Version version1 = new VersionBuilder()
                    .setId("version1477403036635")
                    .setContent("<p>test</p>")
                    .setCreateTime(1477403036635L)
                    .setIsPublic(false)
                    .setCreatorUserId("1009840175700426")
                    .setCreatorUserId("范振原")
                    .setViewCount(0)
                    .createVersion();
            Version version2 = new VersionBuilder()
                    .setId("version1477894888307")
                    .setContent("<p>test</p><p>自從此次</p>\"")
                    .setCreateTime(1477403036635L)
                    .setIsPublic(false)
                    .setCreatorUserId("1009840175700426")
                    .setCreatorUserId("范振原")
                    .setViewCount(0)
                    .createVersion();
            Pack pack = new PackBuilder()
                    .setId("pack1477403034413")
                    .setName("test")
                    .setDescription("test")
                    .setIsPublic(false)
                    .setCreatorUserId("1009840175700426")
                    .setCreatorUserId("范振原")
                    .setVersion(Arrays.asList(version1, version2))
                    .setCoverFilename("uNS5dGs.png")
                    .setViewCount(0)
                    .createPack();
            packRepository.save(pack);


            version1 = new VersionBuilder()
                    .setId("version1478666090008")
                    .setContent("<p>asdfasdf</p>")
                    .setCreateTime(1478666090008L)
                    .setIsPublic(false)
                    .setCreatorUserId("1009840175700426")
                    .setCreatorUserId("范振原")
                    .setViewCount(0)
                    .createVersion();

            pack = new PackBuilder()
                    .setId("pack1479221373194")
                    .setName("private")
                    .setDescription("private")
                    .setIsPublic(false)
                    .setCreatorUserId("1009840175700426")
                    .setCreatorUserId("范振原")
                    .setVersion(Arrays.asList(version1, version2))
                    .setCoverFilename("")
                    .setViewCount(0)
                    .createPack();
            packRepository.save(pack);
        }
    }
}
