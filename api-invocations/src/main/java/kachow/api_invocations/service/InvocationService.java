package kachow.api_invocations.service;

import org.springframework.stereotype.Service;

import kachow.api_invocations.dto.MonstreDTO;
import kachow.api_invocations.repository.MonstreClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;

@Service
public class InvocationService {

    private final MonstreClient monstreClient;
    private final Random random = new Random();

    public InvocationService(MonstreClient monstreClient) {
        this.monstreClient = monstreClient;
    }

    public Mono<MonstreDTO> invoquerMonstre() {
        return monstreClient.getAllMonstres()
                .map(this::selectionnerMonstreAleatoire);
    }

    private MonstreDTO selectionnerMonstreAleatoire(List<MonstreDTO> monstres) {
        double total = monstres.stream().mapToDouble(MonstreDTO::getTauxInvocation).sum();
        double tirage = random.nextDouble() * total;

        double cumul = 0.0;
        for (MonstreDTO monstre : monstres) {
            cumul += monstre.getTauxInvocation();
            if (tirage <= cumul) {
                return monstre;
            }
        }
        return monstres.get(monstres.size() - 1); // Sécurité si dépassement
    }
}
