package br.com.puctech.battle_arena.repository;

import br.com.puctech.battle_arena.model.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {
}
