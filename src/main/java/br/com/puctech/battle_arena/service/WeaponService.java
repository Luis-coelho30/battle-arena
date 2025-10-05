package br.com.puctech.battle_arena.service;

import br.com.puctech.battle_arena.model.Player;
import br.com.puctech.battle_arena.model.Weapon;
import br.com.puctech.battle_arena.repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeaponService {

    @Autowired
    private WeaponRepository weaponRepository;

    @Autowired
    private PlayerService playerService;

    public List<Weapon> getAll() {
        return weaponRepository.findAll();
    }

    public Optional<Weapon> getById(Long id) {
        return weaponRepository.findById(id);
    }

    public String addWeapon(Long playerId) {
        Optional<Player> player = playerService.getPlayerById(playerId);

        if(player.isPresent()) {
            Weapon weapon = weaponRepository.findRandomWeapon();
            if(weapon != null) {
                weapon.setDono(player.get());
                weaponRepository.save(weapon);
                return "Jogador " + player.get().getNome() + " pegou uma nova arma!\n" +
                        "\nArma: " + weapon.getNome() + "\nDano: " + weapon.getDano();
            }
            else {
                throw new IndexOutOfBoundsException("O baú não contém mais nenhuma arma!");
            }
        }

        throw new IllegalArgumentException("Jogador não foi encontrado");
    }
}
