package kachow.api_invocations.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kachow.api_invocations.dto.MonstreDTO;
import kachow.api_invocations.service.InvocationService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/invocation")
public class InvocationController {

    private final InvocationService invocationService;

    public InvocationController(InvocationService invocationService) {
        this.invocationService = invocationService;
    }

    @GetMapping("/monstre")
    public Mono<MonstreDTO> invoquerMonstre() {
        return invocationService.invoquerMonstre();
    }
}