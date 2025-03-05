package kachow.api_invocations.service;

import kachow.api_invocations.client.InvocationClient;
import kachow.api_invocations.dto.MonstreDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;

@Service
public class InvocationService {

    private final InvocationClient invocationClient;
    private final Random random = new Random();

    public InvocationService(InvocationClient invocationClient) {
        this.invocationClient = invocationClient;
    }

    public Mono<MonstreDTO> invokeMonster(String token) {
        return invocationClient.validateToken(token)
                .flatMap(username -> {
                    MonstreDTO monster = generateRandomMonster().block();
                    return invocationClient.createMonster(monster)
                            .doOnNext(createdMonster -> invocationClient.addMonsterToPlayer(username, createdMonster.getId()).subscribe());
                });
    }
    private Mono<MonstreDTO> generateRandomMonster() {
        List<MonstreDTO> monsterPool = getMonsterPool();
        int index = random.nextInt(monsterPool.size());
        return Mono.just(monsterPool.get(index));
    }

    private List<MonstreDTO> getMonsterPool() {
        return null;
    }
}
