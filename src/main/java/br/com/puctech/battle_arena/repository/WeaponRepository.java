package br.com.puctech.battle_arena.repository;

import br.com.puctech.battle_arena.model.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {

    @Query(value = "SELECT * FROM weapon WHERE player_id IS NULL ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Weapon findRandomWeapon();
}
