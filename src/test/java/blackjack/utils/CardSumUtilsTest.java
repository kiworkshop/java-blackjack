package blackjack.utils;

import blackjack.domain.Card;
import blackjack.enums.Denomination;
import blackjack.enums.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CardSumUtilsTest {
    @DisplayName("카드목록이 2 3 4일 경우 9로 계산된다")
    @Test
    void cardSumTestTwoThreeFour() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.TWO, Type.SPADE));
        cards.add(new Card(Denomination.THREE, Type.CLUB));
        cards.add(new Card(Denomination.FOUR, Type.HEART));

        //when

        //then
        assertThat(CardSumUtils.getCardsSum(cards)).isEqualTo(9);
    }

    @DisplayName("카드목록이 Ace 8일 경우 19로 계산된다")
    @Test
    void cardSumTestAceEight() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.ACE, Type.SPADE));
        cards.add(new Card(Denomination.EIGHT, Type.CLUB));

        //when

        //then
        assertThat(CardSumUtils.getCardsSum(cards)).isEqualTo(19);
    }

    @DisplayName("카드목록이 Ace Ace 8일 경우 20으로 계산된다")
    @Test
    void cardSumTestAceAceEight() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.ACE, Type.SPADE));
        cards.add(new Card(Denomination.ACE, Type.CLUB));
        cards.add(new Card(Denomination.EIGHT, Type.HEART));

        //when

        //then
        assertThat(CardSumUtils.getCardsSum(cards)).isEqualTo(20);
    }

    @DisplayName("카드목록이 Ace Ace Ace 7일 경우 20으로 계산된다")
    @Test
    void cardSumTestAceAceAceSeven() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.ACE, Type.SPADE));
        cards.add(new Card(Denomination.ACE, Type.CLUB));
        cards.add(new Card(Denomination.ACE, Type.HEART));
        cards.add(new Card(Denomination.SEVEN, Type.HEART));

        //when

        //then
        assertThat(CardSumUtils.getCardsSum(cards)).isEqualTo(20);
    }

    @DisplayName("카드목록이 Ace Ace Ace Ace일 경우 14로 계산된다")
    @Test
    void cardSumTestAceAceAceAce() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.ACE, Type.SPADE));
        cards.add(new Card(Denomination.ACE, Type.CLUB));
        cards.add(new Card(Denomination.ACE, Type.HEART));
        cards.add(new Card(Denomination.ACE, Type.DIAMOND));

        //when

        //then
        assertThat(CardSumUtils.getCardsSum(cards)).isEqualTo(14);
    }
}