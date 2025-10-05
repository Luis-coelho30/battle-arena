package br.com.puctech.battle_arena.service;

import br.com.puctech.battle_arena.model.Player;
import br.com.puctech.battle_arena.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public String attackPlayer(Long id) {
        Optional<Player> optPlayer = playerRepository.findById(id);

        if (optPlayer.isEmpty()) {
            throw new IllegalArgumentException("Jogador não foi encontrado.");
        }
        Player player = optPlayer.get();

        Random random = new Random();

        if (random.nextBoolean()) {
            int currentLife = player.getVida();
            int newLife = Math.max(currentLife - 5, 0);
            player.setVida(newLife);
            playerRepository.save(player);

            return "Jogador " + player.getNome() + " foi ferido!\nVida = " + newLife;
        }
        else {
            return "Jogador " + player.getNome() + " esquivou com sucesso!";
        }
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
        else {
            return "Jogador " + player.getNome() + " falhou em se curar!";
        }
    }
}
