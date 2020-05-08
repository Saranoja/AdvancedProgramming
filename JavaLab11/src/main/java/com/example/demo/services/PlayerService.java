package com.example.demo.services;

import com.example.demo.custom.CustomException;
import com.example.demo.models.Player;
import com.example.demo.repo.PlayersRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Calin Irina, I2E2
 */

public class PlayerService {

    public static List<Player> getAllPlayers() {
        List<Player> players = PlayersRepo.showList();
        if (players.size() > 0) {
            return players;
        } else {
            return new ArrayList<>();
        }
    }

    public static Player getPlayer(String playerId) {
        Player player = PlayersRepo.showPlayer(playerId);
        if(player!=null)
            return player;
        else throw new CustomException("Player not found for id " + playerId);
    }

    public static Player createPlayer(Player player) {
        PlayersRepo.addPlayer(player);
        return player;
    }

    public static String deletePlayer(String playerId) {
        if (PlayersRepo.showPlayer(playerId) != null) {
            PlayersRepo.deletePlayer(playerId);
            return "Success";
        } else throw new CustomException("Player not found");
    }

    public static String updatePlayer(String playerId, String newName) {
        if (PlayersRepo.showPlayer(playerId) != null) {
            PlayersRepo.updatePlayer(playerId, newName);
            return "Success";
        } else throw new CustomException("Player not found");
    }
}
