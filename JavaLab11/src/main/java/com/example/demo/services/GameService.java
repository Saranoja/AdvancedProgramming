package com.example.demo.services;

/**
 * @author : Calin Irina, I2E2
 */

import com.example.demo.custom.CustomException;
import com.example.demo.models.Game;
import com.example.demo.repo.GamesRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    public static List<Game> getAllGames() {
        List<Game> games = GamesRepo.showList();
        if (games.size() > 0) {
            return games;
        } else {
            return new ArrayList<>();
        }
    }

    public static Game getGame(String gameId) {
        Game game = GamesRepo.showGame(gameId);
        if(game!=null)
            return game;
        else throw new CustomException("Game not found for id " + gameId);
    }

    public static Game createGame(Game game) {
        GamesRepo.addGame(game);
        return game;
    }

    public static String deleteGame(String gameId) {
        if (GamesRepo.showGame(gameId) != null) {
            GamesRepo.deleteGame(gameId);
            return "Success";
        } else throw new CustomException("Game not found");
    }
}
