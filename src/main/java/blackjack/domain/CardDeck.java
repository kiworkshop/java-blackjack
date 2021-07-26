package blackjack.domain;

import blackjack.enums.CardScore;
import blackjack.enums.CardType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardDeck {

    public static final int FIRST_CARDS_SETTING_COUNT = 2;
    private final List<Card> cards = new ArrayList<>();
    private int size;

    public CardDeck() {
        init();
    }

    private void init() {
        Arrays.stream(CardScore.values())
                .forEach(cardScore -> {
                    Arrays.stream(CardType.values()).forEach(cardType -> cards.add(new Card(cardScore, cardType)));
                });

        size = cards.size();
        suffleCard();
    }

    public int getSize() {
        return size;
    }

    private void suffleCard() {
        Collections.shuffle(cards);
    }

    public List<Card> getFirstTwoCards() {
        return IntStream.range(0, FIRST_CARDS_SETTING_COUNT)
                .mapToObj(i -> getAdditionalCard())
                .collect(Collectors.toList());
    }

    public Card getAdditionalCard() {
        return cards.get(--size);
    }
}
