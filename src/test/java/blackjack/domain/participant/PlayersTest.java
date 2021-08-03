package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.GivenCards;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @Test
    @DisplayName("플레이어의 이름을 반환한다.")
    void getPlayerNames() {
        //given
        String name1 = "pobi";
        Player player1 = new Player(name1, Deck.getTwoCards());
        String name2 = "tobi";
        Player player2 = new Player(name2, Deck.getTwoCards());
        Players players = new Players(Arrays.asList(player1, player2));

        //when
        List<String> playerNames = players.getPlayerNames();

        //then
        assertThat(playerNames).hasSize(2)
                .containsOnly(name1, name2);
    }

    @Test
    @DisplayName("플레이어들의 모든 카드를 반환한다.")
    void getPlayerCards() {
        //given
        String name1 = "pobi";
        Player player1 = new Player(name1, Deck.getTwoCards());
        String name2 = "tobi";
        Player player2 = new Player(name2, Deck.getTwoCards());
        Players players = new Players(Arrays.asList(player1, player2));

        //when
        List<List<Card>> allPlayerCards = players.getPlayerCards();

        //then
        assertThat(allPlayerCards).hasSize(2);
        allPlayerCards.forEach(playerCards -> assertThat(playerCards).hasSize(2));
    }

    @ParameterizedTest
    @CsvSource(value = {"A, TEN, true", "TEN, TEN, false"})
    @DisplayName("모든 플레이어가 종료하였는지 여부를 반환한다.")
    void allPlayersFinished(String score1, String score2, boolean expected) {
        //given
        Card card1 = new Card(Score.A, Suit.DIAMOND);
        Card card2 = new Card(Score.J, Suit.DIAMOND);
        Person player1 = new Player("pobi", new GivenCards(Arrays.asList(card1, card2)));
        Card card3 = new Card(Score.valueOf(score1), Suit.CLUB);
        Card card4 = new Card(Score.valueOf(score2), Suit.CLUB);
        Person player2 = new Player("tobi", new GivenCards(Arrays.asList(card3, card4)));
        Players players = new Players(Arrays.asList(player1, player2));

        //when
        boolean isFinished = players.allPlayersFinished();

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
        Person player1 = new Player("player1", new GivenCards(Arrays.asList(card1, card2)));
        Card card3 = new Card(Score.valueOf(score1), Suit.CLUB);
        Card card4 = new Card(Score.valueOf(score2), Suit.CLUB);
        Person player2 = new Player("player2", new GivenCards(Arrays.asList(card3, card4)));
        Card card5 = new Card(Score.valueOf(score3), Suit.HEART);
        Card card6 = new Card(Score.valueOf(score4), Suit.HEART);
        Person player3 = new Player("player3", new GivenCards(Arrays.asList(card5, card6)));
        Players players = new Players(Arrays.asList(player1, player2, player3));

        //when
        String player = players.getCurrentPlayer();

        //then
        assertThat(player).isEqualTo(expectedPlayer);
    }

    @Test
    @DisplayName("인자로 받은 플레이어의 카드를 반환한다.")
    void getCards() {
        //given
        String name = "pobi";
        Card card1 = new Card(Score.A, Suit.HEART);
        Card card2 = new Card(Score.A, Suit.CLUB);
        Person player = new Player(name, new GivenCards(Arrays.asList(card1, card2)));
        Players players = new Players(Arrays.asList(player));

        //when
        List<Card> cards = players.getCards(name);

        //then
        assertThat(cards).hasSize(2)
                .containsOnly(card1, card2);
    }

    @ParameterizedTest
    @CsvSource(value = {"A, TEN, A, NINE, 21, 20", "TEN, TEN, A, A, 20, 12"})
    @DisplayName("플레이어들의 점수를 반환한다.")
    void getPlayerScores(String score1, String score2, String score3, String score4, int expectedSum1, int expectedSum2) {
        //given
        Card card1 = new Card(Score.valueOf(score1), Suit.CLUB);
        Card card2 = new Card(Score.valueOf(score2), Suit.CLUB);
        Person player1 = new Player("player1", new GivenCards(Arrays.asList(card1, card2)));
        Card card3 = new Card(Score.valueOf(score3), Suit.HEART);
        Card card4 = new Card(Score.valueOf(score4), Suit.HEART);
        Person player2 = new Player("player2", new GivenCards(Arrays.asList(card3, card4)));
        Players players = new Players(Arrays.asList(player1, player2));

        //when
        List<Integer> playerScores = players.getPlayerScores();

        //then
        assertThat(playerScores).hasSize(2)
                .containsOnly(expectedSum1, expectedSum2);
    }

    @ParameterizedTest
    @CsvSource(value = {"TEN, FIVE, SIX, 1, 1", "TEN, TEN, TWO, 1, -1"})
    @DisplayName("자신의 카드의 합이 크거나 블랙잭일 경우 1, 작을 경우 -1을 반환한다.")
    void getResults_with_win_and_lose(String score1, String score2, String score3, int expectedResult1, int expectedResult2) {
        //given
        Card card1 = new Card(Score.A, Suit.CLUB);
        Card card2 = new Card(Score.J, Suit.CLUB);
        Person player1 = new Player("player1", new GivenCards(Arrays.asList(card1, card2)));
        Card card3 = new Card(Score.valueOf(score1), Suit.HEART);
        Card card4 = new Card(Score.valueOf(score2), Suit.HEART);
        Card card5 = new Card(Score.valueOf(score3), Suit.HEART);
        Person player2 = new Player("player2", new GivenCards(Arrays.asList(card3, card4, card5)));
        Card card6 = new Card(Score.A, Suit.DIAMOND);
        Card card7 = new Card(Score.NINE, Suit.DIAMOND);
        GivenCards dealerCards = new GivenCards(Arrays.asList(card6, card7));
        Players players = new Players(Arrays.asList(player1, player2));

        //when
        List<Integer> results = players.getResults(dealerCards);

        //then
        assertThat(results).hasSize(2)
                .containsOnly(expectedResult1, expectedResult2);
    }

    @Test
    @DisplayName("자신의 합과 같을 경우, 0을 반환한다.")
    void getResults_with_draw() {
        //given
        Card card1 = new Card(Score.A, Suit.CLUB);
        Card card2 = new Card(Score.FIVE, Suit.CLUB);
        Card card3 = new Card(Score.FIVE, Suit.CLUB);
        Person player = new Player("player", new GivenCards(Arrays.asList(card1, card2, card3)));
        Card card4 = new Card(Score.A, Suit.DIAMOND);
        Card card5 = new Card(Score.EIGHT, Suit.DIAMOND);
        Card card6 = new Card(Score.TWO, Suit.DIAMOND);
        GivenCards dealerCards = new GivenCards(Arrays.asList(card4, card5, card6));
        Players players = new Players(Collections.singletonList(player));

        //when
        List<Integer> results = players.getResults(dealerCards);

        //then
        assertThat(results).hasSize(1)
                .containsOnly(0);
    }
}
