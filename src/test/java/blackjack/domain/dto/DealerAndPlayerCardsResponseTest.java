package blackjack.domain.dto;

import blackjack.domain.card.Card;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class DealerAndPlayerCardsResponseTest {

    @Test
    @DisplayName("딜러 카드를 반환한다.")
    void getDealerCards() {
        //given
        Score ace = Score.A;
        Suit club = Suit.CLUB;
        Suit diamond = Suit.DIAMOND;
        Card card1 = new Card(ace, club);
        Card card2 = new Card(ace, diamond);
        List<Card> dealerCards = Arrays.asList(card1, card2);
        DealerAndPlayerCardsResponse dealerAndPlayerCardsResponse = new DealerAndPlayerCardsResponse(dealerCards, Collections.emptyList());

        //when
        CardsResponse dealerCard = dealerAndPlayerCardsResponse.getDealerCards();

        //then
        assertThat(dealerCard.getCards()).hasSize(2)
                .extracting("denomination", "suit")
                .containsOnly(tuple(ace.getDenomination(), club.getSuit()),
                        tuple(ace.getDenomination(), diamond.getSuit()));
    }

    @Test
    @DisplayName("모든 플레이어의 카드를 반환한다.")
    void getAllPlayerCards() {
        //given
        Score ace = Score.A;
        Suit club = Suit.CLUB;
        Suit diamond = Suit.DIAMOND;
        Card card1 = new Card(ace, club);
        Card card2 = new Card(ace, diamond);
        List<Card> playerCards = Arrays.asList(card1, card2);
        List<List<Card>> allPlayerCards = Arrays.asList(playerCards, playerCards, playerCards);
        DealerAndPlayerCardsResponse dealerAndPlayerCardsResponse = new DealerAndPlayerCardsResponse(Collections.emptyList(), allPlayerCards);

        //when
        List<CardsResponse> allPlayerCardsResponse = dealerAndPlayerCardsResponse.getAllPlayerCards();

        //then
        assertThat(allPlayerCardsResponse).hasSize(3);
        allPlayerCardsResponse.forEach(
                playerCardsResponse -> assertThat(playerCardsResponse.getCards()).hasSize(2)
                        .extracting("denomination", "suit")
                        .containsOnly(tuple(ace.getDenomination(), club.getSuit()),
                                tuple(ace.getDenomination(), diamond.getSuit()))
        );
    }
}
