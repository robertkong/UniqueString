package org.rkong.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    List<Card> cards = new ArrayList<Card>(52);
    int currentPosition = 0;

    public static CardDeck newDeck() {
        CardDeck deck = new CardDeck();
        for (int i = 1; i <= 13; i++) {
            deck.addCards(i);
        }
        deck.shuffle();
        return deck;
    }

    private void addCards(int i) {
        for (Suit suit : Suit.values()) {
            Card card = new Card(i, suit);
            addCard(card);
        }
    }

    private void addCard(Card card) {
        cards.add(card);
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card nextCard() {
        return cards.get(currentPosition++);
    }
}
