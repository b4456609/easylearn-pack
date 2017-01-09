package soselab.easylearn.MCA;

import feign.Contract;
import feign.Target;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.context.named.NamedContextFactory;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientSpecification;
import org.springframework.cloud.netflix.feign.FeignContext;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.SystemProfileValueSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ActiveProfiles("test")
public class TestBean {
    @Autowired
    ApplicationContext applicationContext;



    @Test
    public void printBeans() {
//        System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
//        for(String name : applicationContext.getBeanDefinitionNames()){
//
//            System.out.println(applicationContext.getBean(name));
//        }
//        NoteClient noteClient = (NoteClient) applicationContext.getBean("soselab.easylearn.client.NoteClient");
        System.out.println(applicationContext.getBean("soselab.easylearn.client.NoteClient").getClass());
        System.out.println(applicationContext.getBeanNamesForAnnotation(FeignClient.class));
        for(String name : applicationContext.getBeanNamesForAnnotation(FeignClient.class)){
            System.out.println(applicationContext.getBean(name));
            System.out.println(AopProxyUtils.ultimateTargetClass(applicationContext.getBean(name)));

        }

        System.out.println(applicationContext.getBeansWithAnnotation(FeignClient.class));
//        System.out.println(applicationContext.getBean("default.soselab.easylearn.PackApplication.FeignClientSpecification"));
//        System.out.println(applicationContext.getBean("easylearn-note.FeignClientSpecification"));
//        System.out.println(applicationContext.getBean("feignClient"));
//        System.out.println(applicationContext.getBean("feignRequestOptions"));
//        System.out.println(applicationContext.getBean("org.springframework.cloud.netflix.feign.FeignAutoConfiguration"));
//        System.out.println(applicationContext.getBean("feignFeature"));
//        System.out.println(applicationContext.getBean("feignContext"));

//        Target.HardCodedTarget hardCodedTarget = (Target.HardCodedTarget) applicationContext.getBean("soselab.easylearn.client.NoteClient");
//        System.out.println(hardCodedTarget.name());
//        System.out.println(hardCodedTarget.type());
//        System.out.println(hardCodedTarget.url());

        System.out.println(applicationContext.getEnvironment());
//        System.out.println(applicationContext.getClassLoader().);
//        System.out.println(contracts);
//        NamedContextFactory<FeignClientSpecification> feignContext = (NamedContextFactory<FeignClientSpecification>) applicationContext.getBean("feignContext");



//        Target.HardCodedTarget = applicationContext.getBean("soselab.easylearn.client.NoteClient");

    }
}