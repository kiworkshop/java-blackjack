package blackjack.domain;

import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GivenCardsTest {

    @Test
    @DisplayName("가지고 있는 카드의 합을 반환한다.")
    void sum() {
        //given
        Card card1 = new Card(Score.EIGHT, Suit.CLUB);
        Card card2 = new Card(Score.TWO, Suit.CLUB);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));

        //when
        int sum = givenCards.sum();

        //then
        assertThat(sum).isEqualTo(10);
    }

    @Test
    @DisplayName("Ace가 1로 계산된 카드의 합을 반환한다.")
    void sum_with_ace_value_1() {
        //given
        Card card1 = new Card(Score.TEN, Suit.CLUB);
        Card card2 = new Card(Score.TEN, Suit.CLUB);
        Card card3 = new Card(Score.A, Suit.CLUB);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2, card3));

        //when
        int sum = givenCards.sum();

        //then
        assertThat(sum).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace가 11로 계산된 카드의 합을 반환한다.")
    void sum_with_ace_value_11() {
        //given
        Card card1 = new Card(Score.TEN, Suit.CLUB);
        Card card2 = new Card(Score.A, Suit.CLUB);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));

        //when
        int sum = givenCards.sum();

        //then
        assertThat(sum).isEqualTo(21);
    }

    @Test
    @DisplayName("처음 카드 2장이 21일 경우, 참을 반환한다.")
    void isBlackjack() {
        //given
        Card card1 = new Card(Score.TEN, Suit.CLUB);
        Card card2 = new Card(Score.A, Suit.CLUB);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));

        //when
        boolean isBlackjack = givenCards.isBlackjack();

        //then
        assertThat(isBlackjack).isTrue();
    }

    @Test
    @DisplayName("카드의 합이 21을 초과할 경우, 참을 반환한다.")
    void isBurst() {
        //given
        Card card1 = new Card(Score.TEN, Suit.CLUB);
        Card card2 = new Card(Score.TEN, Suit.HEART);
        Card card3 = new Card(Score.TWO, Suit.HEART);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2, card3));

        //when
        boolean isBurst = givenCards.isBurst();

        //then
        assertThat(isBurst).isTrue();
    }

    @Test
    @DisplayName("가지고 있는 카드 목록을 반환한다.")
    void list() {
        //given
        Card card1 = new Card(Score.TEN, Suit.CLUB);
        Card card2 = new Card(Score.A, Suit.CLUB);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));

        //when
        List<Card> cards = givenCards.list();

        //then
        assertThat(cards).hasSize(2)
                .containsOnly(card1, card2);
    }

    @Test
    @DisplayName("인자로 받은 카드를 추가한다.")
    void add() {
        //given
        Card card1 = new Card(Score.TEN, Suit.CLUB);
        Card card2 = new Card(Score.NINE, Suit.CLUB);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        Card newCard = new Card(Score.TWO, Suit.CLUB);

        //when
        givenCards.add(newCard);

        //then
        assertThat(givenCards.list()).hasSize(3)
                .containsOnly(card1, card2, newCard);
    }
}
