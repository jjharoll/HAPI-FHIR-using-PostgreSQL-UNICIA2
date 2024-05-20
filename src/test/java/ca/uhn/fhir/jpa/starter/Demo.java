package ca.uhn.fhir.jpa.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import ca.uhn.fhir.interceptor.api.IInterceptorService;
import org.springframework.beans.factory.annotation.Autowired;

@ServletComponentScan(basePackageClasses = {JpaRestfulServer.class})
@SpringBootApplication(exclude = ElasticsearchRestClientAutoConfiguration.class)
public class Demo {

    @Autowired
    private IInterceptorService interceptorService;

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "r4");
        System.setProperty("spring.batch.job.enabled", "false");
        SpringApplication.run(Demo.class, args);
    }

    @Bean
    public CustomIdValidationInterceptor customIdValidationInterceptor() {
        CustomIdValidationInterceptor interceptor = new CustomIdValidationInterceptor();
        interceptorService.registerInterceptor(interceptor);
        return interceptor;
    }
}
