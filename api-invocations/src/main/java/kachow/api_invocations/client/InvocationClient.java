package kachow.api_invocations.client;

import kachow.api_invocations.dto.MonstreInvocDTO;
import kachow.api_invocations.model.Monstre;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class InvocationClient {

    private final WebClient authClient;
    private final WebClient joueurClient;
    private final WebClient monstreClient;

    public InvocationClient(WebClient.Builder webClientBuilder) {
        this.authClient = webClientBuilder.baseUrl("http://api-auth:8080").build();
        this.joueurClient = webClientBuilder.baseUrl("http://api-joueur:8080").build();
        this.monstreClient = webClientBuilder.baseUrl("http://api-monstres:8080").build();
    }

    public Mono<Monstre> createMonster(Monstre monstreDTO, String token) {
        System.out.println("Creating monster: " + monstreDTO);
        System.out.println(monstreClient.post()
        .uri("/api/monstre/")
        .header("Authorization", token)
        .bodyValue(monstreDTO)
        .retrieve()
        .bodyToMono(Monstre.class));
        return null;
        // return monstreClient.post()
        //         .uri("/api/monstre/create")
        //         .header("Authorization", token)
        //         .bodyValue(monstreDTO)
        //         .retrieve()
        //         .bodyToMono(Monstre.class);
    }

    public Mono<Void> addMonsterToPlayer(String username, String monsterId) {
        return joueurClient.post()
                .uri("/api/joueur/ajoutmonstre")
                .bodyValue(new MonsterAcquisitionRequest(username, monsterId))
                .retrieve()
                .bodyToMono(Void.class);
    }

    public String validateToken(String token) {
        return authClient.post()
                .uri("/api/auth/validate")
                .body(BodyInserters.fromFormData("token", token))
                .retrieve()
                .bodyToMono(String.class).block();
    }

}