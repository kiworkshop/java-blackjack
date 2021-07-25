package blackjack.domain;

import java.util.Arrays;

public class Card {
    private final String score;
    private final String type;

    private static final String[] scores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K"};
    private static final String[] types = {"다이아몬드", "클로버", "하트", "스페이드"};

    public Card(String score, String type) {
        validateScore(score);
        validateType(type);
        this.score = score;
        this.type = type;
    }

    private void validateScore(String inputScore) {
        boolean isMatched = Arrays.asList(scores).contains(inputScore);

        if (!isMatched) {
            throw new IllegalArgumentException("카드 숫자가 규격에 맞지 않습니다.");
        }
    }

    private void validateType(String inputType) {
        boolean isMatched = Arrays.asList(types).contains(inputType);

        if (!isMatched) {
            throw new IllegalArgumentException("카드 타입이 규격에 맞지 않습니다.");
        }
    }
}
