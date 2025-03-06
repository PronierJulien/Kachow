package kachow.api_invocations.service;

import kachow.api_invocations.client.InvocationClient;
import kachow.api_invocations.dto.MonstreInvocDTO;
import kachow.api_invocations.dto.TauxMonstreDTO;
import kachow.api_invocations.repository.MonsterInvocRepository;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;

@Service
public class InvocationService {

    private final InvocationClient invocationClient;
    private final MonsterInvocRepository monsterInvocRepository;
    private final Random random = new Random();

    public InvocationService(InvocationClient invocationClient, MonsterInvocRepository monsterInvocRepository) {
        this.invocationClient = invocationClient;
        this.monsterInvocRepository = monsterInvocRepository;
    }

    public Mono<MonstreInvocDTO> invokeMonster(String token) {
        return invocationClient.validateToken(token)
                .flatMap(username -> {
                    MonstreInvocDTO monster = generateRandomMonster().block();
                    return invocationClient.createMonster(monster)
                            .doOnNext(createdMonster -> invocationClient.addMonsterToPlayer(username, createdMonster.getId()).subscribe());
                });
    }
    private Mono<MonstreInvocDTO> generateRandomMonster() {
        return getMonsterPool().flatMap(monsterPool -> {
            double totalTaux = monsterPool.stream().mapToDouble(TauxMonstreDTO::getTauxInvocation).sum();
            double randomValue = random.nextDouble() * totalTaux;

            double cumulativeProbability = 0.0;
            for (TauxMonstreDTO tauxMonstre : monsterPool) {
                cumulativeProbability += tauxMonstre.getTauxInvocation();
                if (randomValue <= cumulativeProbability) {
                    return Mono.just(tauxMonstre.getMonstreInvoc());
                }
            }

            return Mono.just(monsterPool.get(monsterPool.size() - 1).getMonstreInvoc());
        });
    }

    private Mono<List<TauxMonstreDTO>> getMonsterPool() {
        return monsterInvocRepository.findAll().collectList();
    }
}
