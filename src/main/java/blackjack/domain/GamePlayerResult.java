package blackjack.domain;

import blackjack.service.GameService;

public class GamePlayerResult {
    private String name;
    private int score;
    private String result;

    public GamePlayerResult(Player player, int dealerScore) {
        this.name = player.getName();
        this.score = player.getScore();
        this.result = getResult(dealerScore);
    }

    public String getResult() {
        return result;
    }

    public String getName() {
        return name;
    }

    private String getResult(int dealerScore) {

        if ((dealerScore < score || dealerScore > GameService.BLACKJACK) && score <= GameService.BLACKJACK) {
            return "승";
        }
        if (dealerScore == score) {
            return "무";
        }
        return "패";
    }

}
