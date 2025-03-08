package kachow.api_invocations.service;

import kachow.api_invocations.client.InvocationClient;
import kachow.api_invocations.dto.MonstreInvocDTO;
import kachow.api_invocations.model.Monstre;
import kachow.api_invocations.repository.MonstreInvocRepository;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;

@Service
public class InvocationService {

    private final InvocationClient invocationClient;
    private MonstreInvocRepository monstreInvocRepository;
    private final Random random = new Random();

    public InvocationService(InvocationClient invocationClient, MonstreInvocRepository monstreInvocRepository) {
        this.invocationClient = invocationClient;
        this.monstreInvocRepository = monstreInvocRepository;
    }

    public Mono<MonstreInvocDTO> invokeMonster(String token) {
        return invocationClient.validateToken(token)
                .flatMap(username -> {
                    MonstreInvocDTO monster = generateRandomMonster().block();
                    Monstre finalMonster = monster.toMonstre(username);
                    return invocationClient.createMonster(monster)
                            .doOnNext(createdMonster -> invocationClient.addMonsterToPlayer(username, createdMonster.get_id()).subscribe());
                });
    }
    private Mono<MonstreInvocDTO> generateRandomMonster() {
        return getMonsterPool().flatMap(monsterPool -> {
            double totalTaux = monsterPool.stream().mapToDouble(MonstreInvocDTO::getLootRate).sum();
            double randomValue = random.nextDouble() * totalTaux;

            double cumulativeProbability = 0.0;
            for (MonstreInvocDTO monstreInvocDTO : monsterPool) {
                cumulativeProbability += monstreInvocDTO.getLootRate();
                if (randomValue <= cumulativeProbability) {
                    return Mono.just(monstreInvocDTO);
                }
            }

            return Mono.just(monsterPool.get(monsterPool.size() - 1));
        });
    }

    private Mono<List<MonstreInvocDTO>> getMonsterPool() {
        return Mono.just(this.monstreInvocRepository.findAll());
    }
}
