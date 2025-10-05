package br.com.puctech.battle_arena.controller;

import br.com.puctech.battle_arena.model.Weapon;
import br.com.puctech.battle_arena.service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weapon_chest")
public class WeaponController {

    @Autowired
    private WeaponService weaponService;

    @GetMapping("/look")
    public List<Weapon> findAll() {
        return weaponService.getAll();
    }

    @GetMapping("/{playerId}/grab")
    public ResponseEntity<String> getWeapon(@PathVariable Long playerId) {
        try {
            return ResponseEntity.ok(weaponService.addWeapon(playerId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
