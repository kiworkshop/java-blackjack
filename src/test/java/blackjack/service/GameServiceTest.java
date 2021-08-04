package blackjack.service;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.GameTotalReuslt;
import blackjack.domain.Player;
import blackjack.enums.Denomination;
import blackjack.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameServiceTest {
    @Test
    @DisplayName("참가자 이름 리스트로 등록하면 게임에 참가자들이 생성된다")
    void create() {
        //given
        GameService gameService = new GameService();
        List<String> playerNames = Arrays.asList("pobi", "jason");

        //when
        List<Player> players = gameService.registPlayer(playerNames);

        //then
        assertThat(players).hasSize(2)
                .extracting("name")
                .contains("pobi", "jason");
        assertThat(players.get(0).getName()).isEqualTo("pobi");
        assertThat(players.get(1).getName()).isEqualTo("jason");
    }

    @Test
    @DisplayName("플레이어와 딜러는  초기 두장의 카드를 받는다")
    void setFirstTwoCardsForPlayer() {
        //given
        GameService gameService = new GameService();
        Player player = new Player("jason");
        Dealer dealer = new Dealer();
        //when
        player = (Player) gameService.setFirstTwoCards(player);
        dealer = (Dealer) gameService.setFirstTwoCards(dealer);
        //then
        assertThat(player.getCards()).hasSize(2);
        assertThat(dealer.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("플레이어와 딜러는 카드1장을 받는다")
    void Hit() {
        //given
        GameService gameService = new GameService();
        Player player = new Player("jason");
        Dealer dealer = new Dealer();
        //when
        player = (Player) gameService.hit(player);
        dealer = (Dealer) gameService.hit(dealer);
        //then
        assertThat(player.getCards()).hasSize(1);
        assertThat(dealer.getCards()).hasSize(1);
    }

    @Test
    @DisplayName("플레어와 딜러의 가지고 있는 카드 점수를 계산해서 반환한다.")
    void playerHit() {
        //given
        GameService gameService = new GameService();
        Player player = new Player("jason");
        Dealer dealer = new Dealer();
        player.addCard(new Card(Denomination.Q, Suit.CLUB));
        player.addCard(new Card(Denomination.A, Suit.CLUB));
        dealer.addCard(new Card(Denomination.THREE, Suit.CLUB));
        dealer.addCard(new Card(Denomination.FIVE, Suit.CLUB));
        //when
        int playerScore = gameService.getCardScore(player);
        int dealerScore = gameService.getCardScore(dealer);
        //then
        assertThat(playerScore).isEqualTo(21);
        assertThat(playerScore).isEqualTo(8);
    }

    @Test
    @DisplayName("플레어와 딜러의 최종 승패 결과를 반환한다.")
    void getCameTotalResult() {
        //given
        GameService gameService = new GameService();

        //when
        List<Player> players = new ArrayList<>();
        Player player = new Player("pobi");
        player.addCard(new Card(Denomination.TWO, Suit.CLUB));
        player.addCard(new Card(Denomination.THREE, Suit.CLUB));
        players.add(player);
        
        Dealer dealer = new Dealer();
      
        dealer.addCard(new Card(Denomination.TEN, Suit.CLUB));
        dealer.addCard(new Card(Denomination.EIGHT, Suit.CLUB));
        //when
        GameTotalReuslt gameTotalReuslt = gameService.getCameTotalResult(dealer, players);
        
        //then
        assertThat(gameTotalReuslt.getDealerLoseCount()).isEqualTo(0);
        assertThat(gameTotalReuslt.getDealerWinCount()).isEqualTo(1);
        assertThat(gameTotalReuslt.getPlayerResults().get(0).getResult()).isEqualTo("패");

    }
    @Test
    @DisplayName("딜러 카드 점수가 16이하이면 참을 반환한다")
    void getMoreCard() {
        //given
        GameService gameService = new GameService();
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.FIVE, Suit.CLUB));
        dealer.addCard(new Card(Denomination.EIGHT, Suit.CLUB));
        //when
        boolean result = gameService.getMoreCard(dealer);
        //then
        assertThat(result);
    }
}