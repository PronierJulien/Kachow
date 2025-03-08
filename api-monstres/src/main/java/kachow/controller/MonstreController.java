package kachow.controller;

import kachow.model.Monstre;
import kachow.service.Client;
import kachow.service.MonstreService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/monstre")
public class MonstreController {
    private final MonstreService monstreService;
    private final Client client;

    public MonstreController(MonstreService monstreService, Client client) {
        this.monstreService = monstreService;
        this.client = client;
    }

    @GetMapping("/get/{monstreId}")
    public ResponseEntity<HashMap<String, Object>> getMonstre(@RequestHeader("Authorization") String token, @PathVariable UUID monstreId) {
        String idJoueur = client.verifyToken(token);
        if(monstreService.isAuthorized(monstreId, idJoueur))
            return ResponseEntity.ok(monstreService.getMonstreInfo(monstreId));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createMonstre(Monstre monstre) {
        monstreService.createMonstre(monstre);
        return ResponseEntity.ok("Monstre created");
    }

    @GetMapping("/addXp/{monstreId}/{xp}")
    public ResponseEntity<String> addXp(@PathVariable UUID monstreId, @PathVariable int xp) {
        monstreService.addXp(monstreId, xp);
        return ResponseEntity.ok("XP added");
    }
}
