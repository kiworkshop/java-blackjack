package blackjack.domain;

import blackjack.service.GameService;

public class GamePlayerResult {
    private String name;
    private int score;
    private String result;
    private Player player;
    private Dealer dealer;

    public GamePlayerResult(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
        this.name = player.getName();
        this.score = player.getScore();
        this.result = getResult(dealer.getScore());
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

    public double getEarnMoney() {
        //블랙잭
        if (isBlackJack()) {
            return player.getBettingMoney() * 1.5;
        }
        //승
        if (isWin()) {
            return player.getBettingMoney() * 1;
        }
        //패
        if (isLosebyBlackJack() || isLose()) {
            return player.getBettingMoney() * -1;
        }
        //무
        return 0;
    }

    private boolean isBlackJack() {
        return !dealer.isBlackjack() && player.isBlackjack();
    }

    private boolean isWin() {
        return dealer.getScore() < player.getScore() && !player.isBust();
    }

    private boolean isLosebyBlackJack() {
        return (dealer.isBlackjack() && !player.isBlackjack());
    }

    private boolean isLose() {
        return (dealer.getScore() > player.getScore() || player.isBust());
    }
}
