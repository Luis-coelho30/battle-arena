package br.com.puctech.battle_arena.service;

import br.com.puctech.battle_arena.model.Player;
import br.com.puctech.battle_arena.model.Weapon;
import br.com.puctech.battle_arena.repository.PlayerRepository;
import br.com.puctech.battle_arena.repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private WeaponRepository weaponRepository;

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public String attackPlayer(Long playerId, Long targetId) {
        Optional<Player> optPlayer = playerRepository.findById(targetId);

        if (optPlayer.isEmpty()) {
            throw new IllegalArgumentException("Jogador não foi encontrado.");
        }
        Player player = optPlayer.get();

        Random random = new Random();

        if (random.nextBoolean()) {
            List<Weapon> weaponList = weaponRepository.findWeaponByPlayer(playerId);
            Weapon weapon = weaponList.stream()
                    .max(Comparator.comparing(Weapon::getDano))
                    .orElse(null);

            int damage = 5;

            if(weapon != null) {
                damage = weapon.getDano();
            }

            int currentLife = player.getVida();
            int newLife = Math.max(currentLife - damage, 0);
            player.setVida(newLife);
            playerRepository.save(player);

            String attackType = (weapon == null) ? "um soco" : "um ataque com " + weapon.getNome();
            return "Jogador " + player.getNome() + " recebeu " + attackType + "!\nVida = " + newLife;
        }

        return "Jogador " + player.getNome() + " esquivou com sucesso!";
    }

    public String healPlayer(Long id) {
        Optional<Player> optPlayer = playerRepository.findById(id);

        if (optPlayer.isEmpty()) {
            throw new IllegalArgumentException("Jogador não foi encontrado.");
        }
        Player player = optPlayer.get();

        Random random = new Random();

        if (random.nextBoolean()) {
            int currentLife = player.getVida();
            int newLife = Math.min(currentLife + 5, 100);
            player.setVida(newLife);
            playerRepository.save(player);

            return "Jogador " + player.getNome() + " se curou!\nVida = " + newLife;
        }


        return "Jogador " + player.getNome() + " falhou em se curar!";
    }
}
