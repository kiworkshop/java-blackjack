package blackjack.controller.dto;

import blackjack.domain.card.Card;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class CardsResponseTest {

    @Test
    @DisplayName("모든 카드를 반환한다.")
    void getCards() {
        //given
        Score ace = Score.A;
        Suit club = Suit.CLUB;
        Suit diamond = Suit.DIAMOND;
        Card card1 = Card.from(ace, club);
        Card card2 = Card.from(ace, diamond);
        List<Card> playerCards = Arrays.asList(card1, card2);
        CardsResponse cardsResponse = new CardsResponse(playerCards);

        //when
        List<CardResponse> cards = cardsResponse.getCards();

        //then
        assertThat(cards).hasSize(2)
                .extracting("denomination", "suit")
                .containsOnly(tuple(ace.getDenomination(), club.getSuit()),
                        tuple(ace.getDenomination(), diamond.getSuit()));
    }
}
