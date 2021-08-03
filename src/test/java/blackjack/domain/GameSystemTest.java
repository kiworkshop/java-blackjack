package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.GivenCards;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GameSystemTest {

    @Test
    @DisplayName("플레이어의 이름을 반환한다.")
    void getPlayerNames() {
        //given
        String name1 = "pobi";
        String name2 = "tobi";
        List<String> names = Arrays.asList(name1, name2);

        //when
        GameSystem gameSystem = GameSystem.create(names);

        //then
        assertThat(gameSystem.getPlayerNames()).hasSize(2)
                .contains(name1, name2);
    }

    @Test
    @DisplayName("딜러의 모든 카드를 반환한다,")
    void getDealerCards() {
        //given
        GameSystem gameSystem = new GameSystem(Collections.emptyList());

        //when
        List<Card> dealerCards = gameSystem.getDealerCards();

        //then
        assertThat(dealerCards).hasSize(2);
    }

    @Test
    @DisplayName("플레이어들의 모든 카드를 반환한다.")
    void getPlayerCards() {
        //given
        String name1 = "pobi";
        String name2 = "tobi";
        List<String> names = Arrays.asList(name1, name2);
        GameSystem gameSystem = GameSystem.create(names);

        //when
        List<List<Card>> allPlayerCards = gameSystem.getPlayerCards();

        //then
        assertThat(allPlayerCards).hasSize(2);
        allPlayerCards.forEach(playerCards -> assertThat(playerCards).hasSize(2));
    }

    @ParameterizedTest
    @CsvSource(value = {"A, TEN, true", "TEN, TEN, false"})
    @DisplayName("모든 플레이어가 종료하였는지 여부를 반환한다.")
    void isFinished(String score1, String score2, boolean expected) {
        //given
        Card card1 = new Card(Score.A, Suit.DIAMOND);
        Card card2 = new Card(Score.J, Suit.DIAMOND);
        Card card3 = new Card(Score.valueOf(score1), Suit.CLUB);
        Card card4 = new Card(Score.valueOf(score2), Suit.CLUB);
        Player player1 = new Player("pobi", new GivenCards(Arrays.asList(card1, card2)));
        Player player2 = new Player("tobi", new GivenCards(Arrays.asList(card3, card4)));
        GameSystem gameSystem = new GameSystem(Arrays.asList(player1, player2));

        //when
        boolean isFinished = gameSystem.isFinished();

        //then
        assertThat(isFinished).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"A, TEN, A, NINE, player3", "TEN, TEN, TEN, TEN, player2"})
    @DisplayName("참여 종료상태가 아닌 플레이어 중 먼저 입력 받았던 플레이어의 이름을 반환한다.")
    void getCurrentPlayer(String score1, String score2, String score3, String score4, String expectedPlayer) {
        //given
        Card card1 = new Card(Score.A, Suit.DIAMOND);
        Card card2 = new Card(Score.J, Suit.DIAMOND);
        Card card3 = new Card(Score.valueOf(score1), Suit.CLUB);
        Card card4 = new Card(Score.valueOf(score2), Suit.CLUB);
        Card card5 = new Card(Score.valueOf(score3), Suit.HEART);
        Card card6 = new Card(Score.valueOf(score4), Suit.HEART);
        Player player1 = new Player("player1", new GivenCards(Arrays.asList(card1, card2)));
        Player player2 = new Player("player2", new GivenCards(Arrays.asList(card3, card4)));
        Player player3 = new Player("player3", new GivenCards(Arrays.asList(card5, card6)));
        GameSystem gameSystem = new GameSystem(Arrays.asList(player1, player2, player3));

        //when
        String player = gameSystem.getCurrentPlayer();

        //then
        assertThat(player).isEqualTo(expectedPlayer);
    }

    @ParameterizedTest
    @CsvSource(value = {"y, 3", "n, 2"})
    @DisplayName("카드 추가 의사와 플레이어 이름을 인자로 전달받아 플레이어의 카드를 추가한다.")
    void hit(String answer, int expectedSize) {
        //given
        String name = "pobi";
        Card card1 = new Card(Score.A, Suit.HEART);
        Card card2 = new Card(Score.A, Suit.CLUB);
        Player player = new Player(name, new GivenCards(Arrays.asList(card1, card2)));
        GameSystem gameSystem = new GameSystem(Collections.singletonList(player));

        //when
        gameSystem.hit(answer, name);

        //then
        assertThat(player.getCards().list()).hasSize(expectedSize);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Y", "N", "y1", "n1", "yes", "no"})
    @DisplayName("y 또는 n이 아닌 다른 응답을 전달받을 경우, 예외가 발생한다.")
    void hit_with_invalid_answer(String answer) {
        //given
        String name = "pobi";
        Card card1 = new Card(Score.A, Suit.HEART);
        Card card2 = new Card(Score.A, Suit.CLUB);
        Player player = new Player(name, new GivenCards(Arrays.asList(card1, card2)));
        GameSystem gameSystem = new GameSystem(Collections.singletonList(player));

        //when //then
        assertThatThrownBy(() -> gameSystem.hit(answer, name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y 또는 n을 입력해주세요.");
    }

    @Test
    @DisplayName("딜러의 카드를 추가한다.")
    void hit_for_dealer() {
        //given
        GameSystem gameSystem = new GameSystem(Collections.emptyList());

        //when
        gameSystem.hit();

        //then
        assertThat(gameSystem.getDealerCards()).hasSize(3);
    }
}
