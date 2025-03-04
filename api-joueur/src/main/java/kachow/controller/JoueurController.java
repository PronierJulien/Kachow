package kachow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

import kachow.service.JoueurService;

@RestController
@RequestMapping("joueur")
public class JoueurController {
    private final JoueurService joueurService;

    public JoueurController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    @GetMapping("/get/{joueurId}")
    public ResponseEntity<HashMap<String, Object>> getJoueur(@PathVariable UUID joueurId) {
        return ResponseEntity.ok(joueurService.getJoueurInfo(joueurId));

    }

    @PostMapping("/create")
    public ResponseEntity<UUID> createJoueur() {
        return ResponseEntity.ok(joueurService.createJoueur());
    }

    @GetMapping("/addXp/{joueurId}/{xp}")
    public ResponseEntity<String> addXp(@PathVariable UUID joueurId, @PathVariable int xp) {
        joueurService.addXp(joueurId, xp);
        return ResponseEntity.ok("XP added");
    }

    @GetMapping("/addMonstre/{joueurId}/{monstreId}")
    public ResponseEntity<String> addMonstre(@PathVariable UUID joueurId, @PathVariable String monstreId) {
        if (joueurService.addMonstre(joueurId, monstreId)) {
            return ResponseEntity.ok("Monstre added");
        }
        return ResponseEntity.ok("Monstres full");
    }

    @GetMapping("/removeMonstre/{joueurId}/{monstreId}")
    public ResponseEntity<String> removeMonstre(@PathVariable UUID joueurId, @PathVariable String monstreId) {
        joueurService.removeMonstre(joueurId, monstreId);
        return ResponseEntity.ok("Monstre removed");
    }


}
