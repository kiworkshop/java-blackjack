package blackjack.domain;

import blackjack.enums.CardScore;
import blackjack.enums.CardType;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private final List<Card> cards = new ArrayList<>();

    public CardDeck() {
        for (CardScore cardScore : CardScore.values()) {
            for (CardType cardType : CardType.values()) {
                cards.add(new Card(cardScore, cardType));
            }

        }
    }

    public int size() {
        return cards.size();
    }

}
