package blackjack.service;

import blackjack.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameService {

    public static final int BLACKJACK = 21;
    public static final int ACE_BONUS_SCORE = 10;

    private static CardDeck cards = new CardDeck();

    public static Participant setFirstTwoCards(Participant participant) {
        participant.addFirstTwoCards(new Cards(Arrays.asList(cards.getAdditionalCard(), cards.getAdditionalCard())));
        return participant;
    }

    public static Participant hit(Participant participant) {
        participant.hit(cards.getAdditionalCard());
        return participant;
    }

    public GameTotalReuslt getGameTotalResult(Dealer dealer, List<Player> players) {
        List<GamePlayerResult> playerResults = new ArrayList<>();
        players.forEach(player -> {
            playerResults.add(new GamePlayerResult(player, dealer.getScore()));
        });

        return new GameTotalReuslt(playerResults);
    }
}
