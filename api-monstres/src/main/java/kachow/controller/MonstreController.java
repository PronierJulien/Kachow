package kachow.controller;

import kachow.dao.MonstreDao;
import kachow.service.MonstreService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/monstre")
public class MonstreController {
    private final MonstreService monstreService;
    private final String AuthUrl = "localhost:8080/api/auth/validate";

    public MonstreController(MonstreService monstreService) {
        this.monstreService = monstreService;
    }

    @GetMapping("/get/{monstreId}")
    public ResponseEntity<HashMap<String, Object>> getMonstre(@RequestHeader("Authorization") String token, @PathVariable UUID monstreId) {
        String idJoueur = monstreService.verifyToken(token, AuthUrl);
        return ResponseEntity.ok(monstreService.getMonstreInfo(monstreId));
    }

    @GetMapping("/addXp/{monstreId}/{xp}")
    public ResponseEntity<String> addXp(@PathVariable UUID monstreId, @PathVariable int xp) {
        monstreService.addXp(monstreId, xp);
        return ResponseEntity.ok("XP added");
    }
}
