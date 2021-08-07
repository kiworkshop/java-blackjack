package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.GivenCards;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Person;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
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
        Deck deck = new Deck();
        Dealer dealer = new Dealer("딜러", new GivenCards(Collections.emptyList()));
        Players players = new Players(names, deck);

        //when
        GameSystem gameSystem = new GameSystem(dealer, players, deck);

        //then
        assertThat(gameSystem.getPlayerNames()).hasSize(2)
                .contains(name1, name2);
    }

    @Test
    @DisplayName("딜러의 모든 카드를 반환한다.")
    void getDealerCards() {
        //give
        Deck deck = new Deck();
        Dealer dealer = new Dealer("딜러", deck.getTwoCards());
        Players players = new Players(Collections.emptyList(), deck);
        GameSystem gameSystem = new GameSystem(dealer, players, deck);

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
        Deck deck = new Deck();
        Dealer dealer = new Dealer("딜러", new GivenCards(Collections.emptyList()));
        Players players = new Players(names, deck);
        GameSystem gameSystem = new GameSystem(dealer, players, deck);

        //when
        List<List<Card>> allPlayerCards = gameSystem.getPlayerCards();

        //then
        assertThat(allPlayerCards).hasSize(2);
        allPlayerCards.forEach(playerCards -> assertThat(playerCards).hasSize(2));
    }

    @ParameterizedTest
    @CsvSource(value = {"A, TEN, true", "TEN, TEN, false"})
    @DisplayName("모든 플레이어가 종료하였는지 여부를 반환한다.")
    void allPlayersFinished(String score1, String score2, boolean expected) {
        //given
        Card card1 = Card.from(Score.A, Suit.DIAMOND);
        Card card2 = Card.from(Score.J, Suit.DIAMOND);
        Card card3 = Card.from(Score.valueOf(score1), Suit.CLUB);
        Card card4 = Card.from(Score.valueOf(score2), Suit.CLUB);
        Person player1 = new Player("pobi", new GivenCards(Arrays.asList(card1, card2)));
        Person player2 = new Player("tobi", new GivenCards(Arrays.asList(card3, card4)));
        Players players = new Players(Arrays.asList(player1, player2));
        Dealer dealer = new Dealer("딜러", new GivenCards(Collections.emptyList()));
        GameSystem gameSystem = new GameSystem(dealer, players, new Deck());

        //when
        boolean isFinished = gameSystem.allPlayersFinished();

        //then
        assertThat(isFinished).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"A, TEN, A, NINE, player3", "TEN, TEN, TEN, TEN, player2"})
    @DisplayName("참여 종료상태가 아닌 플레이어 중 먼저 입력 받았던 플레이어의 이름을 반환한다.")
    void getCurrentPlayer(String score1, String score2, String score3, String score4, String expectedPlayer) {
        //given
        Card card1 = Card.from(Score.A, Suit.DIAMOND);
        Card card2 = Card.from(Score.J, Suit.DIAMOND);
        Card card3 = Card.from(Score.valueOf(score1), Suit.CLUB);
        Card card4 = Card.from(Score.valueOf(score2), Suit.CLUB);
        Card card5 = Card.from(Score.valueOf(score3), Suit.HEART);
        Card card6 = Card.from(Score.valueOf(score4), Suit.HEART);
        Person player1 = new Player("player1", new GivenCards(Arrays.asList(card1, card2)));
        Person player2 = new Player("player2", new GivenCards(Arrays.asList(card3, card4)));
        Person player3 = new Player("player3", new GivenCards(Arrays.asList(card5, card6)));
        Players players = new Players(Arrays.asList(player1, player2, player3));
        Dealer dealer = new Dealer("dealer", new GivenCards(Collections.emptyList()));
        GameSystem gameSystem = new GameSystem(dealer, players, new Deck());

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
        Card card1 = Card.from(Score.A, Suit.HEART);
        Card card2 = Card.from(Score.A, Suit.CLUB);
        Person player = new Player(name, new GivenCards(Arrays.asList(card1, card2)));
        Players players = new Players(Collections.singletonList(player));
        Dealer dealer = new Dealer("딜러", new GivenCards(Collections.emptyList()));
        GameSystem gameSystem = new GameSystem(dealer, players, new Deck());

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
        Card card1 = Card.from(Score.A, Suit.HEART);
        Card card2 = Card.from(Score.A, Suit.CLUB);
        Person player = new Player(name, new GivenCards(Arrays.asList(card1, card2)));
        Players players = new Players(Collections.singletonList(player));
        Dealer dealer = new Dealer("딜러", new GivenCards(Collections.emptyList()));
        GameSystem gameSystem = new GameSystem(dealer, players, new Deck());

        //when //then
        assertThatThrownBy(() -> gameSystem.hit(answer, name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y 또는 n을 입력해주세요.");
    }

    @Test
    @DisplayName("딜러의 카드를 추가한다.")
    void hit_for_dealer() {
        //given
        Card card1 = Card.from(Score.TWO, Suit.CLUB);
        Card card2 = Card.from(Score.TWO, Suit.HEART);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        Players players = new Players(Collections.emptyList());
        Dealer dealer = new Dealer("딜러", givenCards);
        GameSystem gameSystem = new GameSystem(dealer, players, new Deck());

        //when
        gameSystem.hit();

        //then
        assertThat(gameSystem.getDealerCards()).hasSize(3);
    }

    @Test
    @DisplayName("인자로 받은 플레이어의 카드를 반환한다.")
    void getCards() {
        //given
        String name = "pobi";
        Card card1 = Card.from(Score.A, Suit.HEART);
        Card card2 = Card.from(Score.A, Suit.CLUB);
        Person player = new Player(name, new GivenCards(Arrays.asList(card1, card2)));
        Players players = new Players(Collections.singletonList(player));
        Dealer dealer = new Dealer("딜러", new GivenCards(Collections.emptyList()));
        GameSystem gameSystem = new GameSystem(dealer, players, new Deck());

        //when
        List<Card> cards = gameSystem.getCards(name);

        //then
        assertThat(cards).hasSize(2)
                .containsOnly(card1, card2);
    }

    @Test
    @DisplayName("딜러의 점수 합을 반환한다.")
    void getDealerScore() {
        //give
        Card card1 = Card.from(Score.A, Suit.CLUB);
        Card card2 = Card.from(Score.TEN, Suit.CLUB);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        Dealer dealer = new Dealer("딜러", givenCards);
        Players players = new Players(Collections.emptyList());
        GameSystem gameSystem = new GameSystem(dealer, players, new Deck());

        //when
        int dealerScore = gameSystem.getDealerScore();

        //then
        assertThat(dealerScore).isEqualTo(21);
    }

    @ParameterizedTest
    @CsvSource(value = {"A, TEN, A, NINE, 21, 20", "TEN, TEN, A, A, 20, 12"})
    @DisplayName("플레이어들의 점수를 반환한다.")
    void getPlayerScores(String score1, String score2, String score3, String score4, int expectedSum1, int expectedSum2) {
        //given
        Card card1 = Card.from(Score.valueOf(score1), Suit.CLUB);
        Card card2 = Card.from(Score.valueOf(score2), Suit.CLUB);
        Card card3 = Card.from(Score.valueOf(score3), Suit.HEART);
        Card card4 = Card.from(Score.valueOf(score4), Suit.HEART);
        Person player1 = new Player("player1", new GivenCards(Arrays.asList(card1, card2)));
        Person player2 = new Player("player2", new GivenCards(Arrays.asList(card3, card4)));
        Players players = new Players(Arrays.asList(player1, player2));
        Dealer dealer = new Dealer("딜러", new GivenCards(Collections.emptyList()));
        GameSystem gameSystem = new GameSystem(dealer, players, new Deck());

        //when
        List<Integer> playerScores = gameSystem.getPlayerScores();

        //then
        assertThat(playerScores).hasSize(2)
                .containsOnly(expectedSum1, expectedSum2);
    }

    @ParameterizedTest
    @CsvSource(value = {"TEN, TEN, n, 1, 1", "TEN, EIGHT, n, 1, -1", "A, EIGHT, n, 1, 0"})
    @DisplayName("플레이어의 승패 결과를 반환한다.")
    void getResults(String score1, String score2, String answer, int expectedResult1, int expectedResult2) {
        //given
        Card card1 = Card.from(Score.A, Suit.CLUB);
        Card card2 = Card.from(Score.J, Suit.CLUB);
        Person player1 = new Player("player1", new GivenCards(Arrays.asList(card1, card2)));
        Card card3 = Card.from(Score.valueOf(score1), Suit.HEART);
        Card card4 = Card.from(Score.valueOf(score2), Suit.HEART);
        Person player2 = new Player("player2", new GivenCards(Arrays.asList(card3, card4)));
        Card card5 = Card.from(Score.A, Suit.DIAMOND);
        Card card6 = Card.from(Score.EIGHT, Suit.DIAMOND);
        GivenCards dealerCards = new GivenCards(Arrays.asList(card5, card6));
        Players players = new Players(Arrays.asList(player1, player2));
        Dealer dealer = new Dealer("딜러", dealerCards);
        GameSystem gameSystem = new GameSystem(dealer, players, new Deck());
        gameSystem.hit(answer, "player2");

        //when
        List<Integer> results = gameSystem.getResults();

        //then
        assertThat(results).hasSize(2)
                .containsOnly(expectedResult1, expectedResult2);
    }
}
