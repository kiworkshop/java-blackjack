package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @DisplayName("이름을 입력받아 플레이어 객체를 생성한다.")
    @Test
    void createPlayer() {
        //given
        String name = "John Doe";
        Player player = new Player(name);

        //when //then
        assertThat(player.getName()).isEqualTo("John Doe");
    }

    @DisplayName("플레이어가 카드를 지급받아 갖고 있는지 확인")
    @Test
    void receiveCard() {
        //given
        Player player = new Player("John Doe");
        Card card1 = new Card(Denomination.EIGHT, Type.DIAMOND);
        Card card2 = new Card(Denomination.FOUR, Type.SPADE);

        //when
        player.receiveCard(card1);
        player.receiveCard(card2);

        //then
        assertThat(player.getCards()).contains(card1);
        assertThat(player.getCards()).contains(card2);
    }

    @Test
    @DisplayName("Ace, Ace, 8이 포함된 카드 덱의 합이 20이다.")
    void getCardsSumTest1() {
        //given
        Player player = new Player("name");

        player.getCards().add(new Card(Denomination.ACE, Type.CLUB));
        player.getCards().add(new Card(Denomination.ACE, Type.HEART));
        player.getCards().add(new Card(Denomination.EIGHT, Type.CLUB));

        //when
        int result = player.getCardsSum();

        //then
        assertThat(result).isEqualTo(20);
    }

    @Test
    @DisplayName("Ace가 4장 포함된 카드 덱의 합은 14이다.")
    void getCardsSumTest2() {
        //given
        Player player = new Player("name");

        player.getCards().add(new Card(Denomination.ACE, Type.SPADE));
        player.getCards().add(new Card(Denomination.ACE, Type.CLUB));
        player.getCards().add(new Card(Denomination.ACE, Type.HEART));
        player.getCards().add(new Card(Denomination.ACE, Type.DIAMOND));

        //when
        int result = player.getCardsSum();

        //then
        assertThat(result).isEqualTo(14);
    }

    @Test
    @DisplayName("Ace 3장과 7이 포함된 카드 덱의 합은 20이다.")
    void getCardsSumTest3() {
        //given
        Player player = new Player("name");

        player.getCards().add(new Card(Denomination.ACE, Type.SPADE));
        player.getCards().add(new Card(Denomination.ACE, Type.CLUB));
        player.getCards().add(new Card(Denomination.ACE, Type.HEART));
        player.getCards().add(new Card(Denomination.SEVEN, Type.HEART));

        //when
        int result = player.getCardsSum();

        //then
        assertThat(result).isEqualTo(20);
    }
}
