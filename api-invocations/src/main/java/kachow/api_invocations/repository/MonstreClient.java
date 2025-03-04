package kachow.api_invocations.repository;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import kachow.api_invocations.dto.MonstreDTO;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MonstreClient {

    private final WebClient webClient;
    private final String MONSTREURL = "http://localhost:8080/api/monstre/all";

    public MonstreClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(MONSTREURL).build();
    }

    public Mono<List<MonstreDTO>> getAllMonstres() {
        return webClient.get()
                .uri("/monstres")
                .retrieve()
                .bodyToFlux(MonstreDTO.class)
                .collectList();
    }
}