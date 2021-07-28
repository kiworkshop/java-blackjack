package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AceCardTest {
    @ParameterizedTest
    @CsvSource({"10, 11", "11, 1", "20, 1"})
    @DisplayName("나머지 카드 합이 주어지면 1 또는 11 값을 반환한다.")
    void one_ace_card(int sumExceptAceCard, int exceptedAceCardRank) {
        // given
        AceCard aceCard = new AceCard(Suit.HEART);

        // when
        int rank = aceCard.getRank(sumExceptAceCard);

        // then
        assertThat(rank).isEqualTo(exceptedAceCardRank);
    }

}