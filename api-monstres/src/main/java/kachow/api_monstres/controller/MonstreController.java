package kachow.api_monstres.controller;

import kachow.api_monstres.model.Monstre;
import kachow.api_monstres.service.Client;
import kachow.api_monstres.service.MonstreService;

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
    public ResponseEntity<Monstre> createMonstre(@RequestHeader("Authorization") String token, @RequestBody Monstre monstre) {
        try {
            client.verifyToken(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(null);
        }
        monstreService.createMonstre(monstre);
        return ResponseEntity.ok(monstre);
    }

    @GetMapping("/addXp/{monstreId}/{xp}")
    public ResponseEntity<String> addXp(@RequestHeader("Authorization") String token, @PathVariable UUID monstreId, @PathVariable int xp) {
        String idJoueur = client.verifyToken(token);
        if(monstreService.isAuthorized(monstreId, idJoueur)){
            monstreService.addXp(monstreId, xp);
            return ResponseEntity.ok("XP added");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

}
