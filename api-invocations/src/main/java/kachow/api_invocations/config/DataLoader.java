package kachow.api_invocations.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kachow.api_invocations.dto.MonstreInvocDTO;
import kachow.api_invocations.repository.MonstreInvocRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.InputStream;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(MonstreInvocRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                ObjectMapper mapper = new ObjectMapper();
                InputStream inputStream = getClass().getResourceAsStream("/monstres-with-loot-rate.json");
                System.out.println(inputStream.toString());

                List<MonstreInvocDTO> monsters = mapper.readValue(inputStream, new TypeReference<List<MonstreInvocDTO>>() {});
                repository.saveAll(monsters);

                System.out.println("Données insérées : " + monsters.size() + " monstres");
            } else {
                System.out.println("Les données existent déjà");
            }
        };
    }
}