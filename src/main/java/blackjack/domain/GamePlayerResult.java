package blackjack.domain;

import blackjack.service.GameService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePlayerResult {
    private String name;
    private int score;
    private String result;

    public GamePlayerResult(Player player, int dealerScore) {
        this.name = player.getName();
        GameService gameService = new GameService();
        this.score = gameService.getCardScore(player);
        if(dealerScore<score){
            this.result = "승";
        }else if(dealerScore==score){
            this.result = "무";
        }else{
            this.result = "페";
        }
    }

    public String getResult() {
        return result;
    }

    public String getName() {
        return name;
    }
}
