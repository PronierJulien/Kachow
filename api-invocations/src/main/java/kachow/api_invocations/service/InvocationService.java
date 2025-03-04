package kachow.api_invocations.service;

import org.springframework.stereotype.Service;

import kachow.api_invocations.client.InvocationClient;
import kachow.api_invocations.dto.MonstreDTO;
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

    public Mono<MonstreDTO> invokeMonster() {
        return null;
    }

    private MonstreDTO randomMonster() {
        return null;
    }
}
