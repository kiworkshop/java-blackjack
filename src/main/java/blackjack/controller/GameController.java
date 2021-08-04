package blackjack.controller;

import blackjack.domain.*;
import blackjack.service.GameService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class GameController {
    public static void main(String[] args) {
        GameService gameService = new GameService();
        CardDeck cards = new CardDeck();
        Dealer dealer = new Dealer();

        List<String> playersNames = InputView.getPlayerNames();
        List<Player> players = gameService.registPlayer(playersNames);

        //첫장 카드 받기
        dealer = (Dealer) gameService.setFirstTwoCards(dealer);
        players.stream().forEach(GameService::setFirstTwoCards);
        OutputView.printFirstTwoCards(dealer,players);

        //더 받을지 묻기
        players.forEach(player -> {
            String answer = InputView.getAdditionalCard(player);
            while (answer.equals("Y")){
                gameService.setAdditionalCard(player);
                OutputView.printPlayersCards(player);
                answer = InputView.getAdditionalCard(player);
            }
        });

        //딜러 합이 16이하이면 한장 더받기
        int sum = gameService.getCardScore(dealer);
        if(sum<=16){
            gameService.setAdditionalCard(dealer);
            OutputView.printDealerAdditionCard();
        }

        //결과
        GameTotalReuslt gameTotalReuslt = gameService.getCameTotalResult(dealer, players);

        int dealerScore = gameService.getCardScore(dealer);
        OutputView.printCardResult(dealer,dealerScore);
        players.forEach(player -> {
            OutputView.printCardResult(player,gameService.getCardScore(player));
        });

        //승패 출력
        //gameService.getCameTotalResult(dealer, players);
        OutputView.printTotalResult(gameTotalReuslt);
    }
}
