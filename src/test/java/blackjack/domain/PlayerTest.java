package blackjack.domain;

import blackjack.enums.CardScore;
import blackjack.enums.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @Test
    @DisplayName("사람이름을 입력 받아서 입력받은 플레이어 객체를 만든다")
    void create() {
        //given
        String name = "홍길동";

        //when
        Player player = new Player(name);

        //then
        assertThat(player.getName()).isEqualTo(name);
        assertThat(player.getCards()).hasSize(0);
    }

    @Test
    @DisplayName("입력받은 한 장의 카드를 추가한다.")
    void addCard() {
        //given
        CardDeck cardDeck = new CardDeck();
        Player player = new Player("pobi");

        //when
        player.addCard(cardDeck.getAdditionalCard());

        //then
        assertThat(player.getCards()).hasSize(1);
    }

    @Test
    @DisplayName("카드 덱에서 발급받은 카드 2장을 추가한다.")
    void addFirstTwoCards() {
        //given
        CardDeck cardDeck = new CardDeck();
        Player player = new Player("pobi");

        //when
        player.addFirstTwoCards(cardDeck.getFirstTwoCards());

        //then
        assertThat(player.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("3장 이상의 카드를 발급받는 경우 에러가 발생한다.")
    void addOverThreeCards() {
        //given
        CardDeck cardDeck = new CardDeck();
        Player player = new Player("pobi");
        List<Card> cards = new ArrayList<>();
        cards.add(cardDeck.getAdditionalCard());
        cards.add(cardDeck.getAdditionalCard());
        cards.add(cardDeck.getAdditionalCard());

        //when //then
        assertThatThrownBy(() -> player.addFirstTwoCards(cards)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어가 가진 카드의 합을 반환한다.")
    void sumScore() {
        //given
        Player player = new Player("pobi");
        player.addFirstTwoCards(Arrays.asList(new Card(CardScore.J, CardType.CLUB), new Card(CardScore.FIVE, CardType.CLUB)));

        //when
        int sum = player.sumScore();

        //then
        assertThat(sum).isEqualTo(15);
    }

    @Test
    @DisplayName("카드 중 Ace가 있을 경우 참을 반환한다.")
    void hasAce() {
        //given
        Player player = new Player("pobi");
        player.addCard(new Card(CardScore.A, CardType.HEART));

        //when
        boolean hasAce = player.hasAce();

        //then
        assertThat(hasAce).isTrue();
    }

    @Test
    @DisplayName("플레이어가 ace카드를 가지고 있다면 21을 초과하지 않는 최대점수를 리턴한다")
    void sumScoreWithAce() {
        //given
        Player player1 = new Player("jason1");
        Player player2 = new Player("jason2");
        Player player3 = new Player("jason3");

        player1.addFirstTwoCards(Arrays.asList(new Card(CardScore.A, CardType.DIAMOND), new Card(CardScore.Q, CardType.HEART)));
        player2.addFirstTwoCards(Arrays.asList(new Card(CardScore.A, CardType.DIAMOND), new Card(CardScore.TWO, CardType.HEART)));
        player3.addFirstTwoCards(Arrays.asList(new Card(CardScore.A, CardType.DIAMOND), new Card(CardScore.THREE, CardType.HEART)));
        player3.addCard(new Card(CardScore.TEN, CardType.HEART));

        //when
        int score1 = player1.sumScore();
        int score2 = player2.sumScore();
        int score3 = player3.sumScore();

        //then
        assertThat(score1).isEqualTo(21);
        assertThat(score2).isEqualTo(13);
        assertThat(score3).isEqualTo(14);
    }
}
