package blackjack.utils;

import blackjack.domain.Card;
import blackjack.enums.Denomination;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class CardSumUtils {
    private static final int NUMBER_OF_DEFAULT_SUM = 1;
    private static final int ACE_CHOICE_PRIVILEGE = 10;
    private static final int BLACK_JACK = 21;

    public static int getCardsSum(List<Card> cards) {

        int numberOfAce = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        int defaultCardSum = cards.stream()
                .map(Card::getDenomination)
                .mapToInt(Denomination::getScore)
                .sum();

        int[] allPossibleSums = new int[numberOfAce + NUMBER_OF_DEFAULT_SUM];

        allPossibleSums[0] = defaultCardSum;

        for (int i = 1; i < allPossibleSums.length; i++) {
            allPossibleSums[i] = allPossibleSums[i - 1] + ACE_CHOICE_PRIVILEGE;
        }

        return Arrays.stream(allPossibleSums)
                .filter(sum -> sum <= BLACK_JACK)
                .max()
                .orElseThrow(NoSuchElementException::new)
                ;
    }
}
