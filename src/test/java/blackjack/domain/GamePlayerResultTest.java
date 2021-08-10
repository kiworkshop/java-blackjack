package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Suit;
import blackjack.service.GameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GamePlayerResultTest {
    @Test
    @DisplayName("플레이어와 딜러의 점수를 비교해서 플레이어의 승패결과를 리턴한다")
    void getResult() {
        GameService gameService = new GameService();
        Player player = new Player("jason");
        player.hit(new Card(Denomination.EIGHT, Suit.CLUB));
        player.hit(new Card(Denomination.A, Suit.CLUB));
        player.stay();
        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.TEN, Suit.CLUB));
        dealer.hit(new Card(Denomination.TEN, Suit.HEART));
        dealer.stay();
        Dealer dealer2 = new Dealer();
        dealer2.hit(new Card(Denomination.TEN, Suit.CLUB));
        dealer2.hit(new Card(Denomination.SEVEN, Suit.HEART));
        dealer2.stay();
        //when
        GamePlayerResult gamePlayerResult = new GamePlayerResult(player, dealer);
        GamePlayerResult gamePlayerResult2 = new GamePlayerResult(player, dealer2);
        //then
        assertThat(gamePlayerResult.getResult()).isEqualTo("패");
        assertThat(gamePlayerResult2.getResult()).isEqualTo("승");
        assertThat(gamePlayerResult.getName()).isEqualTo("jason");
        assertThat(gamePlayerResult2.getName()).isEqualTo("jason");
    }

    @Test
    @DisplayName("플레이어와 딜러의 점수를 비교해서 플레이어의 게임결과에 따른 배팅 금액을 리턴한다")
    void getEarnMoney() {
        GameService gameService = new GameService();
        Player player = new Player("jason", 1000);
        player.hit(new Card(Denomination.EIGHT, Suit.CLUB));
        player.hit(new Card(Denomination.A, Suit.CLUB));
        player.stay();
        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.TEN, Suit.CLUB));
        dealer.hit(new Card(Denomination.TEN, Suit.HEART));
        dealer.stay();
        Dealer dealer2 = new Dealer();
        dealer2.hit(new Card(Denomination.TEN, Suit.CLUB));
        dealer2.hit(new Card(Denomination.SEVEN, Suit.HEART));
        dealer2.stay();
        //when
        GamePlayerResult gamePlayerResult = new GamePlayerResult(player, dealer);
        GamePlayerResult gamePlayerResult2 = new GamePlayerResult(player, dealer2);
        //then
        assertThat(gamePlayerResult.getEarnMoney()).isEqualTo(-1000);
        assertThat(gamePlayerResult2.getEarnMoney()).isEqualTo(1000);

    }

    @Test
    @DisplayName("플레이어만 블랙잭인경우 배팅 금액의 1.5배를 리턴한다")
    void getEarnMoneyBlackJack() {
        GameService gameService = new GameService();
        Player player = new Player("jason", 1000);
        player.hit(new Card(Denomination.TEN, Suit.CLUB));
        player.hit(new Card(Denomination.A, Suit.CLUB));

        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.TEN, Suit.CLUB));
        dealer.hit(new Card(Denomination.TEN, Suit.HEART));
        dealer.stay();

        //when
        GamePlayerResult gamePlayerResult = new GamePlayerResult(player, dealer);
        // GamePlayerResult gamePlayerResult2 = new GamePlayerResult(player, dealer2);
        //then
        assertThat(gamePlayerResult.getEarnMoney()).isEqualTo(1500);
        //assertThat(gamePlayerResult2.getEarnMoney()).isEqualTo(1000);

    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭인경우 배팅 금액 0을 리턴한다")
    void getEarnMoneyBlackJackBoth() {
        GameService gameService = new GameService();
        Player player = new Player("jason", 1000);
        player.hit(new Card(Denomination.TEN, Suit.CLUB));
        player.hit(new Card(Denomination.A, Suit.CLUB));

        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.A, Suit.CLUB));
        dealer.hit(new Card(Denomination.TEN, Suit.HEART));

        //when
        GamePlayerResult gamePlayerResult = new GamePlayerResult(player, dealer);

        //then
        assertThat(gamePlayerResult.getEarnMoney()).isEqualTo(0);

    }

    @Test
    @DisplayName("플레이어가 bust인경우 배팅금액- 만큼리턴한다")
    void getEarnMoneyBust() {
        GameService gameService = new GameService();
        Player player = new Player("jason", 1000);
        player.hit(new Card(Denomination.TEN, Suit.CLUB));
        player.hit(new Card(Denomination.NINE, Suit.CLUB));
        player.hit(new Card(Denomination.K, Suit.CLUB));

        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.NINE, Suit.CLUB));
        dealer.hit(new Card(Denomination.TEN, Suit.HEART));
        dealer.stay();
        //when
        GamePlayerResult gamePlayerResult = new GamePlayerResult(player, dealer);

        //then
        assertThat(gamePlayerResult.getEarnMoney()).isEqualTo(-1000);

    }
}
