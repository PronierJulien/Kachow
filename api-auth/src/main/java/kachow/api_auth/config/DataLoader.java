package kachow.api_auth.config;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kachow.api_auth.dto.Token;
import kachow.api_auth.dto.User;
import kachow.api_auth.repository.TokenRepository;
import kachow.api_auth.repository.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadDataToken(TokenRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                ObjectMapper mapper = new ObjectMapper();
                InputStream inputStream = getClass().getResourceAsStream("/IMT_Auth.tokens.json");
                System.out.println(inputStream.toString());

                List<Token> tokens = mapper.readValue(inputStream, new TypeReference<List<Token>>() {});
                repository.saveAll(tokens);

                System.out.println("Données insérées : " + tokens.size() + " tokens");
            } else {
                System.out.println("Les données existent déjà");
            }
        };
    }

    @Bean
    CommandLineRunner loadDataUser(UserRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                ObjectMapper mapper = new ObjectMapper();
                InputStream inputStream = getClass().getResourceAsStream("/IMT_Auth.users.json");
                System.out.println(inputStream.toString());

                List<User> users = mapper.readValue(inputStream, new TypeReference<List<User>>() {});
                repository.saveAll(users);

                System.out.println("Données insérées : " + users.size() + " monstres");
            } else {
                System.out.println("Les données existent déjà");
            }
        };
    }
}