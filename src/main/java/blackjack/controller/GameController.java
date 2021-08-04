package blackjack.controller;

import blackjack.domain.*;
import blackjack.service.GameService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    public static GameService gameService = new GameService();
    public static Dealer dealer = new Dealer();
    public static List<Player> players = new ArrayList<>();

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.play();
    }

    public void play() {
        setUp();
        setFirstTwoCards();
        hitOrStay();
        dealerCardSetting();
        printPlayersScore();
        printGameResult();
    }

    private void setUp() {
        List<String> playersNames = InputView.getPlayerNames();
        players = gameService.registPlayer(playersNames);

    }

    private void setFirstTwoCards() {
        dealer = (Dealer) gameService.setFirstTwoCards(dealer);
        players.stream().forEach(GameService::setFirstTwoCards);
        OutputView.printFirstTwoCards(dealer, players);
    }

    private void hitOrStay() {
        players.forEach(player -> {
            String answer = InputView.getAdditionalCard(player);
            while (answer.equals("Y")) {
                gameService.hit(player);
                OutputView.printPlayersCards(player);
                answer = InputView.getAdditionalCard(player);
            }
        });
    }

    private void dealerCardSetting() {
        if (gameService.getMoreCard(dealer)) {
            gameService.hit(dealer);
            OutputView.printDealerAdditionCard();
        }
    }

    private void printPlayersScore() {
        int dealerScore = gameService.getCardScore(dealer);
        OutputView.printCardResult(dealer, dealerScore);
        players.forEach(player -> {
            OutputView.printCardResult(player, gameService.getCardScore(player));
        });
    }

    private void printGameResult() {
        OutputView.printTotalResult(gameService.getCameTotalResult(dealer, players));
    }
}
