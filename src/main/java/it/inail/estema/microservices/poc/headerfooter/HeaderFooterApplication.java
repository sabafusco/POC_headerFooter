package it.inail.estema.microservices.poc.headerfooter;


import it.inail.estema.microservices.poc.headerfooter.repositories.SectionRepository;
import it.inail.estema.microservices.poc.headerfooter.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoRepositories( basePackages = "it.inail.estema.microservices.poc.headerfooter.repositories")
@EnableScheduling
@ConfigurationProperties
public class HeaderFooterApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeaderFooterApplication.class, args);
	}
}
