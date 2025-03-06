package kachow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

import kachow.service.JoueurService;

@RestController
@RequestMapping("/api/joueur")
public class JoueurController {
    private final JoueurService joueurService;

    private final String AuthUrl = "localhost:8080/api/auth/validate";


    public JoueurController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    @GetMapping("/get/{joueurId}")
    public ResponseEntity<HashMap<String, Object>> getJoueur(@PathVariable String joueurId) {
        return ResponseEntity.ok(joueurService.getJoueurInfo(joueurId));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createJoueur(@RequestHeader("Authorization") String token) {
        String joueurId = joueurService.verifyToken(token, AuthUrl);
        return ResponseEntity.ok(joueurService.createJoueur(joueurId));
    }

    @GetMapping("/addXp/{joueurId}/{xp}")
    public ResponseEntity<String> addXp(@PathVariable String joueurId, @PathVariable int xp) {
        joueurService.addXp(joueurId, xp);
        return ResponseEntity.ok("XP added");
    }

    @GetMapping("/addMonstre/{joueurId}/{monstreId}")
    public ResponseEntity<String> addMonstre(@PathVariable String joueurId, @PathVariable String monstreId) {
        if (joueurService.addMonstre(joueurId, monstreId)) {
            return ResponseEntity.ok("Monstre added");
        }
        return ResponseEntity.ok("Monstres full");
    }

    @GetMapping("/removeMonstre/{joueurId}/{monstreId}")
    public ResponseEntity<String> removeMonstre(@PathVariable String joueurId, @PathVariable String monstreId) {
        joueurService.removeMonstre(joueurId, monstreId);
        return ResponseEntity.ok("Monstre removed");
    }



}
