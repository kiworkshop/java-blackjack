package blackjack.domain.card;

import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    @DisplayName("점수와 무늬를 인자로 받아 한 장의 카드를 생성한다.")
    void create() {
        //given
        Score ace = Score.A;
        Suit diamond = Suit.DIAMOND;

        //when
        Card card = Card.from(ace, diamond);

        //then
        assertThat(card).isEqualTo(Card.from(ace, diamond));
    }

    @Test
    @DisplayName("카드의 점수를 반환한다.")
    void getScore() {
        //given
        Score jack = Score.J;
        Suit diamond = Suit.DIAMOND;
        Card card = Card.from(jack, diamond);

        //when
        int score = card.getScore();

        //then
        assertThat(score).isEqualTo(jack.getScore());
    }

    @Test
    @DisplayName("카드의 점수 이름(끗수)를 반환한다.")
    void getDenomination() {
        //given
        Score jack = Score.J;
        Suit diamond = Suit.DIAMOND;
        Card card = Card.from(jack, diamond);

        //when
        String denomination = card.getDenomination();

        //then
        assertThat(denomination).isEqualTo(jack.getDenomination());
    }

    @ParameterizedTest
    @CsvSource(value = {"A, true", "TWO, false", "TEN, false", "J, false"})
    @DisplayName("카드가 Ace일 경우 참을 반환한다.")
    void isAce(Score score, boolean expected) {
        //given
        Suit diamond = Suit.DIAMOND;
        Card card = Card.from(score, diamond);

        //when
        boolean actual = card.isAce();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
