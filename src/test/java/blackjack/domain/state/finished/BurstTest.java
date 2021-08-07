package blackjack.domain.state.finished;

import blackjack.domain.card.Card;
import blackjack.domain.card.GivenCards;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import blackjack.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class BurstTest {

    @Test
    @DisplayName("패배를 의미하는 -1을 반환한다.")
    void result() {
        //given
        Card card1 = Card.from(Score.EIGHT, Suit.CLUB);
        Card card2 = Card.from(Score.EIGHT, Suit.HEART);
        GivenCards myCards = new GivenCards(Arrays.asList(card1, card2));
        State burst = new Burst(myCards);
        GivenCards otherCards = new GivenCards(Collections.emptyList());

        //when
        int result = burst.compare(otherCards);

        //then
        assertThat(result).isEqualTo(-1);
    }
}
