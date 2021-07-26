package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.game.Deck.INITIAL_DEAL_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

class TableTest {
    @Test
    @DisplayName("딜러에게 초기 카드 배분한다.")
    void create_table() {
        // given, when
        Table table = new Table();

        // then
        assertThat(table.getCardSize()).isEqualTo(50);
        assertThat(table.getDealer().countHands()).isEqualTo(INITIAL_DEAL_COUNT);
    }
}
