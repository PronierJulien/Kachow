package kachow.api_invocations.controller;

import kachow.api_invocations.client.InvocationClient;
import kachow.api_invocations.dto.MonstreInvocDTO;
import kachow.api_invocations.service.InvocationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/invocation")
public class InvocationController {

    private final InvocationService invocationService;
    private final InvocationClient invocationClient;

    public InvocationController(InvocationService invocationService, InvocationClient invocationClient) {
        this.invocationClient = invocationClient;
        this.invocationService = invocationService;
    }

    @GetMapping("/monstre")
    public ResponseEntity<String> invoquerMonstre(@RequestHeader("Authorization") String token) {
                String joueurId;
        try {
            joueurId = invocationClient.validateToken(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok("Invalid token");
        }
        return ResponseEntity.ok(invocationService.invokeMonster(joueurId, token).toString());
    }
}