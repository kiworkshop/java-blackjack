package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DeckFactoryTest {

    @DisplayName("생성된 덱에 포함된 카드 숫자가 52장인지 확인한다")
    @Test
    void createTest() {
        //given, when, then
        assertThat(DeckFactory.createDeck()).hasSize(52);
    }

    @Test
    @DisplayName("같은 Denomination, 같은 type을 가진 카드는 같다.")
    void equalsTest() {
        //given, when, then
        assertThat(DeckFactory.createDeck()).isEqualTo(DeckFactory.createDeck());
    }
}