package blackjack.domain.state;

import blackjack.domain.Card;
import blackjack.domain.GivenCards;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackTest {

    @Test
    @DisplayName("승리를 의미하는 1을 반환한다.")
    void result() {
        //given
        Card card1 = new Card(Score.EIGHT, Suit.CLUB);
        Card card2 = new Card(Score.EIGHT, Suit.HEART);
        GivenCards myCards = new GivenCards(Arrays.asList(card1, card2));
        State blackjack = new Blackjack(myCards);
        GivenCards otherCards = new GivenCards(Collections.emptyList());

        //when
        int result = blackjack.result(otherCards);

        //then
        assertThat(result).isEqualTo(1);
    }
}
