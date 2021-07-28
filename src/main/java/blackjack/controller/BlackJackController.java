package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.PlayersFactory;
import blackjack.dto.DrawCardResponseDTO;
import blackjack.dto.PlayersNameInputDTO;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {
    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;
    private final InputView inputview = new InputView();
    private final OutputView outputView = new OutputView();

    public BlackJackController() {
        PlayersNameInputDTO namesInput = inputview.getPlayersName();
        this.players = PlayersFactory.createPlayers(namesInput.getPlayersName());
        this.dealer = new Dealer();
        this.deck = new Deck();
    }

    public void run() {
        // 시작해서 카드 두 장씩 분배
        drawTowCards();
        // 
    }

    private void drawTowCards() {
        for (Player player : players) {
            player.receiveCard(deck.drawCard());
            player.receiveCard(deck.drawCard());
        }
        dealer.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());
        OutputView.printFirstCardsGiven(players, dealer);
        // 딜러의 카드 목록을 출력
        // 플레이어의 카드 목록을 출력
    }

    private void drawCardToPlayers() {
        for (Player player : players) {
            drawCardToPlayers(player);
        }
    }

    private void drawCardToPlayers(Player player) {
        DrawCardResponseDTO drawCardResponse = inputview.getPlayersResponse(player);
        while (player.drawable() && isYes(drawCardResponse)) {
            player.receiveCard(deck.drawCard());
            // 해당 플레이어의 카드 목록을 출력
        }
    }

    private boolean isYes(DrawCardResponseDTO drawCardResponse) {
        return drawCardResponse.getResponse().equals("y");
    }

}
