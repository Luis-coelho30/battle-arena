package br.com.puctech.battle_arena.controller;

import br.com.puctech.battle_arena.model.Player;
import br.com.puctech.battle_arena.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Player>> getAll() {

        List<Player> playerList = playerService.getPlayers();

        return ResponseEntity.ok().body(playerList);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getByID(@PathVariable Long playerId) {
        Optional<Player> player = playerService.getPlayerById(playerId);

        if (player.isPresent()) {
            return ResponseEntity.ok().body(player.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create_user")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        Player savedPlayer = playerService.createPlayer(player);

        return ResponseEntity.ok(savedPlayer);
    }

    @PutMapping("/attack/{id}")
    public ResponseEntity<String> attackPlayer(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(playerService.attackPlayer(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/heal/{id}")
    public ResponseEntity<String> healPlayer(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(playerService.healPlayer(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
