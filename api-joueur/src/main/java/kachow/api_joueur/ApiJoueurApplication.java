package kachow.api_joueur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "kachow")
@EnableMongoRepositories(basePackages = "kachow.dao")
public class ApiJoueurApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiJoueurApplication.class, args);
	}

}
