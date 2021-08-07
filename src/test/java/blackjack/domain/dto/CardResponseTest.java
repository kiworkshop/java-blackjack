package blackjack.domain.dto;

import blackjack.domain.card.Card;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardResponseTest {

    @Test
    @DisplayName("카드의 점수 이름(끗수)을 반환한다.")
    void getScore() {
        //given
        Card card = Card.from(Score.A, Suit.CLUB);
        CardResponse cardResponse = new CardResponse(card.getDenomination(), card.getSuit());

        //when
        String denomination = cardResponse.getDenomination();

        //then
        assertThat(denomination).isEqualTo(Score.A.getDenomination());
    }

    @Test
    @DisplayName("카드의 무늬를 반환한다.")
    void getSuit() {
        //given
        Card card = Card.from(Score.A, Suit.CLUB);
        CardResponse cardResponse = new CardResponse(card.getDenomination(), card.getSuit());

        //when
        String suit = cardResponse.getSuit();

        //then
        assertThat(suit).isEqualTo(Suit.CLUB.getSuit());
    }
}
