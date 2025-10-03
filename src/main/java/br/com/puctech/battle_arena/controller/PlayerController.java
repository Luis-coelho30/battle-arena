package br.com.puctech.battle_arena.controller;

import br.com.puctech.battle_arena.model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class PlayerController {

    @GetMapping("/findAll")
    public ResponseEntity<List<Player>> getAll() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getByID(@PathVariable Long playerId) {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/create_user")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id1}/attack/{id2}")
    public ResponseEntity<String> attackPlayer(@PathVariable Long id1, @PathVariable Long id2) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/heal/{id}")
    public ResponseEntity<String> healPlayer(@PathVariable Long id) {
        return ResponseEntity.ok(null);
    }
}
