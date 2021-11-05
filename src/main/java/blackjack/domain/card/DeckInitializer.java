package blackjack.domain.card;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DeckInitializer {
    private DeckInitializer() {

    }

    public static Map<String, Card> init() {
        return Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Denomination.values())
                        .map(denomination -> Card.of(suit, denomination))
                ).collect(Collectors.toMap(
                        Card::key, Function.identity()
                ));
    }
}
