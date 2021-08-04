package blackjack.domain;

import blackjack.enums.Denomination;
import blackjack.enums.Suit;
import blackjack.service.GameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HumanTest {
    @Test
    @DisplayName("플레이어는 처음에 카드가 0개인 상태로 시작한다")
    void create() {
        //given
        Human player = new Human("jason");
        Human dealer = new Human();
        //when

        //then
        assertThat(player.getCards()).hasSize(0);
        assertThat(dealer.getCards()).hasSize(0);
    }
    @Test
    @DisplayName("플레이어에게 카드를 추가한다")
    void addCard() {
        //given
        Human player = new Human("jason");
        Human dealer = new Human();
        //when
        player.addCard(new Card(Denomination.EIGHT, Suit.CLUB));
        dealer.addCard(new Card(Denomination.EIGHT, Suit.CLUB));

        //then
        assertThat(player.getCards()).hasSize(1);
        assertThat(dealer.getCards()).hasSize(1);
    }
    @Test
    @DisplayName("플레이어가 가진 카드를 반환한다")
    void getCards() {
        //given
        Human player = new Human("jason");
        Human dealer = new Human();
        //when
        player.addCard(new Card(Denomination.EIGHT, Suit.CLUB));
        player.addCard(new Card(Denomination.TWO, Suit.HEART));
        player.addCard(new Card(Denomination.FOUR, Suit.DIAMOND));
        dealer.addCard(new Card(Denomination.A, Suit.CLUB));

        //then
        assertThat(player.getCards()).hasSize(3)
                .extracting("name")
                .contains("8클로버","2하트","4다이아몬드");
        assertThat(dealer.getCards()).hasSize(1)
                .extracting("name")
                .contains("A클로버");
    }
}
