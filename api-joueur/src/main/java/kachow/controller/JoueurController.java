package kachow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kachow.service.*;

@RestController
@RequestMapping("/api/joueur")
public class JoueurController {
    private final JoueurService joueurService;
    private final Client client;


    public JoueurController(JoueurService joueurService, Client client) {
        this.joueurService = joueurService;
        this.client = client;
    }

    @GetMapping("/get")
    public ResponseEntity<String> getJoueur(@RequestHeader("Authorization") String token) {
        String joueurId;
        try {
            joueurId = client.verifyToken(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok("Invalid token");
        }
        if (joueurService.getJoueur(joueurId) == null) {
            return ResponseEntity.ok("Joueur doesn't exist");
        }
        return ResponseEntity.ok(joueurService.getJoueurInfo(joueurId).toString());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createJoueur(@RequestHeader("Authorization") String token) {
        String joueurId;
        try {
            joueurId = client.verifyToken(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok("Invalid token");
        }
        if (joueurService.getJoueur(joueurId) != null) {
            return ResponseEntity.ok("Joueur already exists");
        }
        return ResponseEntity.ok(joueurService.createJoueur(joueurId));
    }

    @GetMapping("/addXp/{xp}")
    public ResponseEntity<String> addXp(@RequestHeader("Authorization") String token, @PathVariable int xp) {
        String joueurId;
        try {
            joueurId = client.verifyToken(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok("Invalid token");
        }
        if (joueurService.getJoueur(joueurId) == null) {
            return ResponseEntity.ok("Joueur already exists");
        }
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

    @GetMapping("/removeMonstre/{monstreId}")
    public ResponseEntity<String> removeMonstre(@RequestHeader("Authorization") String token,  @PathVariable String monstreId) {
        String joueurId;
        try {
            joueurId = client.verifyToken(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok("Invalid token");
        }
        if (joueurService.getJoueur(joueurId) == null) {
            return ResponseEntity.ok("Joueur already exists");
        }
        joueurService.removeMonstre(joueurId, monstreId);
        return ResponseEntity.ok("Monstre removed");
    }



}
