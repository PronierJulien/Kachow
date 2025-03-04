package kachow.api_invocations.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import kachow.api_invocations.dto.MonstreDTO;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class InvocationClient {

    private final WebClient monstreClient;
    private final WebClient joueurClient;
    private final String MONSTRE_CREATE_URL = "http://localhost:8080/api/monstre/create";
    private final String MONSTRE_ACQUISITION_URL = "http://localhost:8080/api/joueur/ajoutmonstre";

    public InvocationClient(WebClient.Builder webClientBuilder) {
        this.monstreClient = webClientBuilder.baseUrl(MONSTRE_CREATE_URL).build();
        this.joueurClient = webClientBuilder.baseUrl(MONSTRE_ACQUISITION_URL).build();
    }

    

}