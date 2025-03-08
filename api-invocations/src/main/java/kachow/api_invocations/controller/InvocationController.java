package kachow.api_invocations.controller;

import kachow.api_invocations.dto.MonstreInvocDTO;
import kachow.api_invocations.service.InvocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/invocation")
public class InvocationController {

    private final InvocationService invocationService;

    public InvocationController(InvocationService invocationService) {
        this.invocationService = invocationService;
    }

    @GetMapping("/monstre")
    public Mono<MonstreInvocDTO> invoquerMonstre(@RequestHeader("Authorization") String token) {
        return invocationService.invokeMonster(token);
    }
}