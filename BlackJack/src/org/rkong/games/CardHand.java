package org.rkong.games;

import java.util.ArrayList;
import java.util.List;

enum Role {Player, Dealer}

public class CardHand {
    List<Card> cards = new ArrayList<Card>();
    Role role;
//    private int value = 0;

    public CardHand(Role role) {
        this.role = role;
    }

    public void add(Card card) {
        cards.add(card);

    }

    public int getValue() {
        int value = 0;
        int numberOfAces = 0;
        for (Card card : cards) {
            // A can be 11 or 1
            if (card.number == 1) {
                ++numberOfAces;
            } else {
                value += card.getValue();
            }
        }

        if (numberOfAces > 0) {
            // Assume one of the aces is 11
            value += numberOfAces + 10;

            if (value > 21) {
                // Count all aces are 1s.
                value -= 10;
            }
        }

        return value;
    }

    public String toString() {
        return role + " hand: " + cards;
    }

    public void showOneCard() {
        System.out.println(role + " has " + cards.get(0));
    }
}

