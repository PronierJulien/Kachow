package kachow.api_invocations.client;

import kachow.api_invocations.dto.MonstreInvocDTO;

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
        this.authClient = webClientBuilder.baseUrl("http://localhost:8080").build();
        this.joueurClient = webClientBuilder.baseUrl("http://localhost:8081").build();
        this.monstreClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    public Mono<MonstreInvocDTO> createMonster(MonstreInvocDTO monstreDTO) {
        return monstreClient.post()
                .uri("/api/monstre/create")
                .bodyValue(monstreDTO)
                .retrieve()
                .bodyToMono(MonstreInvocDTO.class);
    }

    public Mono<Void> addMonsterToPlayer(String username, String monsterId) {
        return joueurClient.post()
                .uri("/api/joueur/ajoutmonstre")
                .bodyValue(new MonsterAcquisitionRequest(username, monsterId))
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<String> validateToken(String token) {
        return authClient.post()
                .uri("/api/auth/validate")
                .body(BodyInserters.fromFormData("token", token))
                .retrieve()
                .bodyToMono(String.class);
    }

}