package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class CardTest {

    @ParameterizedTest
    @ValueSource(strings = {"동그라미", "네모"})
    @DisplayName("카드의 타입이 다이아몬드,스페이스,클로버,하트 중 하나가 아니면 에러를 리턴한다")
    void validateType(String type) {
        //when //then
        assertThatIllegalArgumentException().isThrownBy(() -> new Card("1", type));
    }

    @ParameterizedTest
    @ValueSource(strings = {"B", "10", "1"})
    @DisplayName("카드의 스코어가 a,j,q,k,2~9 외의 값이면 에러를 리턴한다")
    void validateScore(String score) {
        //when //then
        assertThatIllegalArgumentException().isThrownBy(() -> new Card(score, "하트"));
    }

}
