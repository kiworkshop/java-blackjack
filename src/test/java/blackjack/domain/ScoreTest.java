package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    @DisplayName("스코어 객체가 가진 값이 같으면 동등 객체로 판단한다")
    void equalsTest() {
        //given // when // then
        assertThat(Score.of(3))
                .isEqualTo(Score.of(3));
    }
}