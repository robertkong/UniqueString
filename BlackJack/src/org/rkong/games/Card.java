package org.rkong.games;

enum Suit {Club, Diamond, Heart, Spade}

public class Card {
    int number;
    Suit suit;

    public Card(int i, Suit suit) {
        this.number = i;
        this.suit = suit;
    }

    public int getValue() {
        if (number >= 11) {
            return 10;
        } else {
            return number;
        }
    }

    public String toString() {
        return getSymbol() + " of " + suit + "s";
    }

    private String getSymbol() {
        if (number == 11) {
            return "J";
        } else if (number == 12) {
            return "Q";
        } else if (number == 13) {
            return "K";
        } else if (number == 1) {
            return "A";
        } else {
            return number + "";
        }
    }
}
