package ca.uhn.fhir.jpa.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackageClasses = {JpaRestfulServer.class})
@SpringBootApplication(exclude = ElasticsearchRestClientAutoConfiguration.class)
public class Demo {

  public static void main(String[] args) {

    System.setProperty("spring.profiles.active", "r4");
    System.setProperty("spring.batch.job.enabled", "false");
    SpringApplication.run(Demo.class, args);

    //Server is now accessible at eg. http://192.168.162.23:8080/fhir/metadata
  }
}
