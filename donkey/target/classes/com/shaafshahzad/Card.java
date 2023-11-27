package com.shaafshahzad;

public class Card {

    private String suit;
    private int value;

    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        String valueString;
        switch (value) {
            case 1:
                valueString = "A";
                break;
            case 11:
                valueString = "J";
                break;
            case 12:
                valueString = "Q";
                break;
            case 13:
                valueString = "K";
                break;
            default:
                valueString = String.valueOf(value);
                break;
        }

        String suitSymbol;
        switch (suit) {
            case "Hearts":
                suitSymbol = "♥";
                break;
            case "Diamonds":
                suitSymbol = "♦";
                break;
            case "Clubs":
                suitSymbol = "♣";
                break;
            case "Spades":
                suitSymbol = "♠";
                break;
            default:
                suitSymbol = "?";
                break;
        }

        return valueString + suitSymbol;

    }

}