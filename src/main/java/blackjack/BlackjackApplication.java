package blackjack;

import blackjack.controller.BlackJackController;

public class BlackjackApplication {

    public static void main(String[] args) {
        try {
            new BlackJackController().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
