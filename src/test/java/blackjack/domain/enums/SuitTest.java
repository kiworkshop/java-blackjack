package blackjack.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SuitTest {

    @ParameterizedTest
    @CsvSource(value = {"SPADE, 스페이드", "DIAMOND, 다이아몬드", "CLUB, 클로버", "HEART, 하트"})
    @DisplayName("카드 무늬 이름을 반환한다.")
    void getSuit(Suit cardSuit, String expectedSuit) {
        //when
        String suit = cardSuit.getSuit();

        //then
        assertThat(suit).isEqualTo(expectedSuit);
    }
}
