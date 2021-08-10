package blackjack.controller;

import blackjack.domain.Cards;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
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
        playPlayerTurns();
        playDealerTurn();
        printPlayersScore();
        printGameResult();
        printBettingResult();
    }

    private void setUp() {

        List<String> playersNames = InputView.getPlayerNames();
        playersNames.forEach(
                playersName -> players.add(new Player(playersName,InputView.getBettingMoney(playersName)))
        );
    }

    private void setFirstTwoCards() {
        dealer = (Dealer) gameService.setFirstTwoCards(dealer);
        players.stream().forEach(GameService::setFirstTwoCards);
        OutputView.printFirstTwoCards(dealer, players);
    }

    private void hitOrStay(Player player){
        if(player.isBust() || player.isBlackjack()) {
            return;
        }
        String answer = InputView.getAdditionalCard(player);
        Cards cards = new Cards(player.getCards());
        while (answer.equals("Y")) {
            gameService.hit(player);
            OutputView.printPlayersCards(player);
            if(player.isBust() || player.isBlackjack()) break;
            answer = InputView.getAdditionalCard(player);
            cards = new Cards(player.getCards());
        }
        if(!player.isBust() || !player.isBlackjack()) {
            player.stay();
        }
    }
    private void playPlayerTurns() {
        players.forEach(player->hitOrStay(player));
    }

    private void playDealerTurn() {
        if (dealer.isUnder16()) {
            gameService.hit(dealer);
            OutputView.printDealerAdditionCard();
        }
        if(!dealer.isBust() || !dealer.isBlackjack()){
            dealer.stay();
        }
    }

    private void printPlayersScore() {
        int dealerScore = dealer.getScore();
        OutputView.printCardResult(dealer);
        players.forEach(player -> {
            OutputView.printCardResult(player);
        });
    }

    private void printGameResult() {
        OutputView.printTotalResult(gameService.getGameTotalResult(dealer, players));
    }
    private void printBettingResult() {
        OutputView.printBettingResult(gameService.getGameBettingResult(dealer, players));
    }
}
