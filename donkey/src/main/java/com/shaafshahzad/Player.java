package com.shaafshahzad;

import java.util.Collections;
import java.util.Stack;

public class Player {

    private Stack<Card> hand;
    private Stack<Card> faceUpPile;

    public Player() {
        this.hand = new Stack<>();
        this.faceUpPile = new Stack<>();
    }

    // method to draw card from players deck
    public void drawFromDeck(Deck deck) {
        Card card = deck.drawCard();
        if (card != null) {
            hand.push(card);
        }
    }

    // method to push drawn card onto players faceup pile
    public boolean playCardToFaceUpPile() {
        if (!hand.isEmpty()) {
            faceUpPile.push(hand.pop());
            return true;
        }
        return false;
    }

    //
    public Card getTopCardOfFaceUpPile() {
        return faceUpPile.isEmpty() ? null : faceUpPile.peek();
    }

    // method to draw a card
    public Card drawCard() {
        // if hand isn't empty then return the top card
        if (!hand.isEmpty()) {
            return hand.pop();
        }
        return null;
    }

    // check if player can play on face up pile
    public boolean canPlayOnFaceupPile(Card card) {
        if (faceUpPile.isEmpty()) {
            return true;
        }
        // if the card value is equal to the top card value + 1 then return true
        return card.getValue() == faceUpPile.peek().getValue() + 1;
    }

    // method to play card on face up pile
    public void playCardOnFaceUpPile(Card card, boolean isOwnPile) {
        if (isOwnPile || canPlayOnFaceupPile(card)) {
            faceUpPile.push(card);
        }
    }

    // method to flip face up pile once player deck is cycled through
    public void flipFaceUpPile() {
        while (!faceUpPile.isEmpty()) {
            hand.push(faceUpPile.pop());
        }
        Collections.reverse(hand);
    }

    public boolean isHandEmpty() {
        return hand.isEmpty();
    }

    public Stack<Card> getHand() {
        return hand;
    }

    public Stack<Card> getFaceUpPile() {
        return faceUpPile;
    }

}