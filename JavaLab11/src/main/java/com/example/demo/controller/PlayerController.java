package com.example.demo.controller;

/**
 * @author : Calin Irina, I2E2
 */

import com.example.demo.models.Player;
import com.example.demo.repo.PlayersRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/players")
public class PlayerController {
    ArrayList<Player> players = new ArrayList<>();

    public PlayerController() {
    }

    @GetMapping
    public List<Player> getPlayers() {
        return PlayersRepo.showList();
    }

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable("id") String id) throws SQLException {
        return PlayersRepo.showPlayer(id);
    }

    @PostMapping
    public ResponseEntity<String> createPlayer(@RequestParam("name") String name) {
        Random rand = new Random();
        String id = String.valueOf(rand.nextInt(9999));
        players.add(new Player(id, name));
        PlayersRepo.addPlayer(id, name);
        return new ResponseEntity<>(
                "Player added successfully, id: " + id, HttpStatus.CREATED);
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<String>
    createPlayer(@RequestBody Player player) {
        players.add(player);
        PlayersRepo.addPlayer(player);
        return new ResponseEntity<>(
                "Player added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayer(
            @PathVariable String id, @RequestParam("name") String name) {
        PlayersRepo.updatePlayer(id, name);
        return new ResponseEntity<>(
                "Player's name updated successfully to " + name, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable String id) {
        PlayersRepo.deletePlayer(id);
        return new ResponseEntity<>("Player has been deleted successfully", HttpStatus.OK);
    }
}
