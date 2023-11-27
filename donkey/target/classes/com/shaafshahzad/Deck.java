package com.shaafshahzad;

import java.util.Stack;
import java.util.Collections;

public class Deck {

    private Stack<Card> cards;

    public Deck() {
        this.cards = new Stack<>();
        initializeDeck();
    }

    // Initializes the deck with a standard set of 52 playing cards.
    private void initializeDeck() {
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        for (String suit : suits) {
            for (int value = 1; value <= 13; value++) {
                cards.push(new Card(suit, value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.isEmpty() ? null : cards.pop();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

}