package blackjack.domain.participant;

import blackjack.domain.Card;
import blackjack.domain.GivenCards;
import blackjack.domain.enums.Score;
import blackjack.domain.enums.Suit;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Stay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class GamerTest {

    @Test
    @DisplayName("추가 카드 발급을 거부할 경우, Stay 상태가 된다.")
    void stay() {
        //given
        Card card1 = new Card(Score.TEN, Suit.DIAMOND);
        Card card2 = new Card(Score.NINE, Suit.DIAMOND);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        Person gamer = createGamer("pobi", givenCards, false);

        //when
        gamer.stay();

        //then
        assertThat(gamer).extracting("state")
                .isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("발급 받은 카드 목록을 반환한다.")
    void getCards() {
        //given
        Card card1 = new Card(Score.TEN, Suit.DIAMOND);
        Card card2 = new Card(Score.NINE, Suit.DIAMOND);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        Person gamer = createGamer("pobi", givenCards, false);

        //when
        GivenCards cards = gamer.getCards();

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
        Person gamer = createGamer("pobi", givenCards, false);

        //when
        int sum = gamer.sum();

        //then
        assertThat(sum).isEqualTo(19);
    }

    @Test
    @DisplayName("이름을 반환한다.")
    void getName() {
        //given
        GivenCards givenCards = new GivenCards(Collections.emptyList());
        String playerName = "pobi";
        Person gamer = createGamer("pobi", givenCards, false);

        //when
        String name = gamer.getName();

        //then
        assertThat(name).isEqualTo(playerName);
    }

    @Test
    @DisplayName("종료 여부를 반환한다.")
    void isFinished() {
        //given
        Card card1 = new Card(Score.A, Suit.DIAMOND);
        Card card2 = new Card(Score.TEN, Suit.DIAMOND);
        GivenCards givenCards = new GivenCards(Arrays.asList(card1, card2));
        Person gamer = createGamer("pobi", givenCards, true);

        //when
        boolean finished = gamer.isFinished();

        //then
        assertThat(finished).isTrue();
    }

    private Gamer createGamer(String name, GivenCards givenCards, boolean isFinished) {
        return new Gamer(name) {
            @Override
            public GivenCards getCards() {
                return givenCards;
            }

            @Override
            public String getName() {
                return super.getName();
            }

            @Override
            public int sum() {
                return givenCards.sum();
            }

            @Override
            public void stay() {
                super.stay();
            }

            @Override
            public boolean isFinished() {
                return isFinished;
            }

            @Override
            public State injectStateBy(GivenCards cards) {
                return null;
            }

            @Override
            public void hit(Card card) {

            }
        };
    }
}
