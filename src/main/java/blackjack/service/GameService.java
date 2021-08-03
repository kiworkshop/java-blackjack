package blackjack.service;

import blackjack.domain.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameService {
    public List<Player> registPlayer(List<String> playerNames){
        List<Player> players = new ArrayList<>();
        playerNames.forEach(playerName -> {
            System.out.println(playerName);
            players.add(new Player(playerName));
        });
        return players;
    }

}
