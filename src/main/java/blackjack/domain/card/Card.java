package blackjack.domain.card;

import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {
    private static final Map<String, Card> CARDS = createCards();

    private static Map<String, Card> createCards() {
        return Arrays.stream(Score.values())
                .flatMap(Card::createCard)
                .collect(Collectors.toMap(card -> generateKey(card.score, card.suit), Function.identity()));
    }

    private static Stream<Card> createCard(final Score cardScore) {
        return Arrays.stream(Suit.values())
                .map(cardSuit -> new Card(cardScore, cardSuit));
    }

    private static String generateKey(final Score score, final Suit suit) {
        return score.getDenomination() + suit.getSuit();
    }

    private final Score score;
    private final Suit suit;

    private Card(final Score score, final Suit suit) {
        this.score = score;
        this.suit = suit;
    }

    public static Card from(final Score score, final Suit suit) {
        String key = generateKey(score, suit);
        return CARDS.get(key);
    }

    public static Collection<Card> getDeck() {
        return Collections.unmodifiableCollection(CARDS.values());
    }

    public int getScore() {
        return score.getScore();
    }

    public String getDenomination() {
        return score.getDenomination();
    }

    public String getSuit() {
        return suit.getSuit();
    }

    public boolean isAce() {
        return score.isAce();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return score == card.score && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, suit);
    }
}
