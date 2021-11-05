package blackjack;

import blackjack.controller.BlackjackController;

public class BlackjackApplication {
    public static void main(String[] args) {
        try {
            BlackjackController blackjackController = new BlackjackController();
            blackjackController.run();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
