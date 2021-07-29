package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayersFactory;
import blackjack.domain.result.GameResult;
import blackjack.dto.DrawCardResponseDTO;
import blackjack.dto.PlayersNameInputDTO;
import blackjack.utils.StringtUtil;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {
    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public BlackJackController() {
        PlayersNameInputDTO namesInput = inputView.getPlayersName();
        this.players = PlayersFactory.createPlayers(namesInput.getPlayersName());
        this.dealer = new Dealer();
        this.deck = new Deck();
    }

    public void run() {
        // 시작해서 카드 두 장씩 분배, 카드 목록의 출력
        drawTowCards();
        // 플레이어에게 한장씩 카드를 분배(대답이 Yes인 경우)
        drawCardToPlayers();
        // 딜러에게 한장씩 카드 분배(카드 합이 17미만인 경우만)
        drawCardToDealer();
        // 플레이어가 가진 카드목록과 카드값의 총합 출력
        outputView.printCardsResult(dealer, players);
        // 게임 결과를 출력
        outputView.printGameResult(GameResult.of(dealer, players));
    }

    private void drawTowCards() {
        for (Player player : players) {
            player.receiveCard(deck.drawCard());
            player.receiveCard(deck.drawCard());
        }
        dealer.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());
        outputView.printFirstCardsGiven(players, dealer);
        outputView.printDealerCard(dealer);
        outputView.printPlayersCard(players);
    }

    private void drawCardToPlayers() {
        for (Player player : players) {
            drawCardToPlayer(player);
        }
    }

    private void drawCardToPlayer(Player player) {
        DrawCardResponseDTO drawCardResponse;
        do {
            player.receiveCard(deck.drawCard());

            outputView.printCards(player);
            drawCardResponse = inputView.getPlayersResponse(player);
        } while (player.drawable() && isYes(drawCardResponse));
    }

    private boolean isYes(DrawCardResponseDTO drawCardResponse) {
        String response = drawCardResponse.getResponse().trim();
        return response.equalsIgnoreCase("y");
    }

    private void drawCardToDealer() {
        while (dealer.drawable()) {
            dealer.receiveCard(deck.drawCard());
            outputView.printDealerCardGiven();
        }
    }
}
