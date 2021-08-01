package blackjack.domain;

import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.finished.Burst;
import blackjack.domain.state.finished.Stay;
import blackjack.domain.state.running.Hit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    @DisplayName("처음 받은 카드 두 장의 합이 21일 경우, Blackjack 상태가 된다.")
    void create_state_blackjack() {
        //given
        Card card1 = new Card(Score.A, Suit.DIAMOND);
        Card card2 = new Card(Score.TEN, Suit.DIAMOND);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));

        //when
        Player player = new Player("pobi", givenCards);

        //then
        assertThat(player).extracting("state")
                .isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("처음 받은 카드 두 장의 합이 21 미만일 경우, Hit 상태가 된다.")
    void create_state_hit() {
        //given
        Card card1 = new Card(Score.TEN, Suit.DIAMOND);
        Card card2 = new Card(Score.TEN, Suit.CLUB);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));

        //when
        Player player = new Player("pobi", givenCards);

        //then
        assertThat(player).extracting("state")
                .isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("발급 받은 카드를 더한 합이 21을 초과할 경우, Burst 상태가 된다.")
    void hit_become_burst_state() {
        //given
        Card card1 = new Card(Score.TEN, Suit.DIAMOND);
        Card card2 = new Card(Score.TEN, Suit.CLUB);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        Player player = new Player("pobi", givenCards);
        Card card3 = new Card(Score.TWO, Suit.HEART);

        //when
        player.hit(card3);

        //then
        assertThat(player).extracting("state")
                .isInstanceOf(Burst.class);
    }

    @Test
    @DisplayName("발급 받은 카드를 더한 합이 21 이하일 경우, Hit 상태가 된다.")
    void hit_remain_hit_state() {
        //given
        Card card1 = new Card(Score.TEN, Suit.DIAMOND);
        Card card2 = new Card(Score.NINE, Suit.DIAMOND);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        Player player = new Player("pobi", givenCards);
        Card card3 = new Card(Score.TWO, Suit.HEART);

        //when
        player.hit(card3);

        //then
        assertThat(player).extracting("state")
                .isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("추가 카드 발급을 거부할 경우, Stay 상태가 된다.")
    void stay() {
        //given
        Card card1 = new Card(Score.TEN, Suit.DIAMOND);
        Card card2 = new Card(Score.NINE, Suit.DIAMOND);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        Player player = new Player("pobi", givenCards);

        //when
        player.stay();

        //then
        assertThat(player).extracting("state")
                .isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("발급 받은 카드 목록을 반환한다.")
    void getCards() {
        //given
        Card card1 = new Card(Score.TEN, Suit.DIAMOND);
        Card card2 = new Card(Score.NINE, Suit.DIAMOND);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        Player player = new Player("pobi", givenCards);

        //when
        GivenCards cards = player.getCards();

        //then
        assertThat(cards).isEqualTo(givenCards);
    }

    @Test
    @DisplayName("카드의 합을 반환한다.")
    void sum() {
        //given
        Card card1 = new Card(Score.TEN, Suit.DIAMOND);
        Card card2 = new Card(Score.NINE, Suit.DIAMOND);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        Player player = new Player("pobi", givenCards);

        //when
        int sum = player.sum();

        //then
        assertThat(sum).isEqualTo(19);
    }
}
