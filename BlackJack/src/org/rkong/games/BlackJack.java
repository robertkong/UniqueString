package org.rkong.games;

public class BlackJack {
    private CardDeck cardDeck;
    private CardHand playerHand;
    private CardHand dealerHand;

    public void play() {
        cardDeck = SetupCardDeck();

        playerHand = dealInitialHand(Role.Player);

        boolean gameOver = checkHand(playerHand);

        if (gameOver) {

            return;
        }
        printHand(playerHand);

        dealerHand = dealInitialHand(Role.Dealer);
        gameOver = checkHand(dealerHand);
        if (gameOver) {
            return;
        }

        dealerHand.showOneCard();
        gameOver = addCardsToPlayerHand();

        if (gameOver) {
            return;
        }

        gameOver = addCardsToDealerHand();
        if (gameOver) {
            return;
        }

        checkWhoWins();

    }

    private void checkWhoWins() {
        printHand(playerHand);
        printHand(dealerHand);
        System.out.println("Player has " + playerHand.getValue() + " points.");
        System.out.println("Dealer has " + dealerHand.getValue() + " points.");
        System.out.println(playerHand.getValue() > dealerHand.getValue() ? "Player wins!!" : "Player lost!");
    }

    private void printHand(CardHand hand) {
        System.out.println(hand);
    }

    private boolean addCardsToDealerHand() {
        System.out.println("Now it's dealer's turn.");
        while (true) {
            if (dealerHand.getValue() >= 17) {
                break;
            }
            Card card = cardDeck.nextCard();
            System.out.println("Dealer was dealt: " + card + "");

            dealerHand.add(card);
            boolean gameOver = checkHand(dealerHand);
            if (gameOver) {
                return true;
            }
        }
        return false;
    }

    private boolean addCardsToPlayerHand() {

        while (true) {
            System.out.println("You have " + playerHand.getValue() + " points.");
            boolean result = Main.answerQuestion(" Do you want another card? Y/N");
            if (!result) {
                break;
            }

            Card card = cardDeck.nextCard();
            System.out.println("Player was dealt: " + card + "");
            playerHand.add(card);
            boolean gameOver = checkHand(playerHand);
            if (gameOver) {
                return true;
            }
        }
        return false;
    }

    private boolean checkHand(CardHand hand) {
        int value = hand.getValue();
        if (value >= 21) {
            printHand(hand);
            if (value == 21) {
                System.out.println(hand.role == Role.Player ? "Player wins!!" : "Player lost!");
            } else {
                System.out.println(hand.role == Role.Player ? "Player lost!" : "Player wins!!");
            }
            return true;
        }
        return false;
    }

    private CardHand dealInitialHand(Role role) {
        CardHand hand = new CardHand(role);
        for (int i = 0; i < 2; i++) {
            hand.add(cardDeck.nextCard());
        }
        return hand;
    }

    private CardDeck SetupCardDeck() {
        return cardDeck.newDeck();
    }
}
