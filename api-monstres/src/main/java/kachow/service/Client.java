package kachow.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class Client {

    private final WebClient authClient;


    public Client(WebClient.Builder webClientBuilder) {
        this.authClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public String verifyToken(String token) {
        return authClient.post()
            .uri("/api/auth/validate")
            .body(BodyInserters.fromFormData("token", token))
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }
    
}
