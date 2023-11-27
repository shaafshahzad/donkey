package com.shaafshahzad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Game {

    private Player player1;
    private Player player2;
    private Deck deck;
    private List<Stack<Card>> middlePiles;

    public Game() {
        this.player1 = new Player();
        this.player2 = new Player();
        this.deck = new Deck();
        this.deck.shuffle();
        distributeCards();

        this.middlePiles = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            middlePiles.add(new Stack<Card>());
        }

    }

    // distribute cards evenely to both players
    private void distributeCards() {
        while (!deck.isEmpty()) {
            player1.drawFromDeck(deck);
            player2.drawFromDeck(deck);
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        // while game is not over, clear terminal and display the current game state
        // check if game is over
        while (!isGameOver()) {
            clearTerminal();
            displayGameState();

            takeTurn(player1, scanner);
            if (isGameOver()) {
                break;
            }

            clearTerminal();
            displayGameState();

            takeTurn(player2, scanner);
            displayGameState();
        }

    }

    private void takeTurn(Player player, Scanner scanner) {
        System.out.println("Player's turn: " + (player == player1 ? "Player 1" : "Player 2"));
        Player opponent = (player == player1) ? player2 : player1;

        Card drawnCard = null;

        // initial choice to draw a card or play top card
        if (!player.isHandEmpty()) {
            System.out.println("What would you like to do: 1) Draw a card 2) Play top card");
            int initialChoice = scanner.nextInt();

            if (initialChoice == 1) {
                drawnCard = player.drawCard();
                System.out.println("Drawn card: " + drawnCard);
            } else {
                if (!player.getFaceUpPile().isEmpty()
                        && canPlayOnPlayerPile(player.getTopCardOfFaceUpPile(), opponent)) {
                    opponent.playCardOnFaceUpPile(player.getTopCardOfFaceUpPile(), true);
                    player.getFaceUpPile().pop();
                    return;
                } else {
                    System.out.println("Cannot play top card. Drawing a card instead.");
                    drawnCard = player.drawCard();
                    System.out.println("Drawn card: " + drawnCard);
                }
            }
        }

        // if player has no cards in hand and face up pile is not empty, flip pile
        if (player.isHandEmpty() && !player.getFaceUpPile().isEmpty()) {
            player.flipFaceUpPile();
            clearTerminal();
            displayGameState();
            System.out.println(player == player1 ? "Player 1" : "Player 2" + " flipped their face-up pile.");
        }

        if (drawnCard == null) {
            return;
        }

        boolean playedCard = false;

        // while a card has not been chose, ask player to choose an action
        while (!playedCard) {
            System.out.println("Choose an action: 1) Own pile 2) Opponent's pile 3) Middle pile");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    player.playCardOnFaceUpPile(drawnCard, true);
                    playedCard = true;
                    break;
                case 2:
                    if (canPlayOnPlayerPile(drawnCard, opponent)) {
                        opponent.playCardOnFaceUpPile(drawnCard, true);
                        playedCard = true;
                    } else {
                        System.out.println("Can't play on opponent's pile. Choose another action.");
                    }
                    break;
                case 3:
                    if (canPlayOnMiddlePile(drawnCard)) {
                        player.playCardOnFaceUpPile(drawnCard, false);
                        playedCard = true;
                    } else {
                        System.out.println("Can't play on middle pile. Choose another action.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Choose another action.");
            }
        }
    }

    // check if player can play on opponents pile
    private boolean canPlayOnPlayerPile(Card card, Player player) {
        if (player.getFaceUpPile().isEmpty()) {
            return true;
        }
        return card.getValue() == player.getTopCardOfFaceUpPile().getValue() + 1;
    }

    // check if player can play on middle pile
    private boolean canPlayOnMiddlePile(Card card) {
        Stack<Card> suitPile = middlePiles.get(getSuitIndex(card));
        if (suitPile.isEmpty()) {
            return card.getValue() == 1;
        }
        return card.getValue() == suitPile.peek().getValue() + 1;
    }

    private int getSuitIndex(Card card) {
        switch (card.getSuit()) {
            case "Hearts":
                return 0;
            case "Diamonds":
                return 1;
            case "Clubs":
                return 2;
            case "Spades":
                return 3;
            default:
                return -1;
        }
    }

    // clear terminal to keep clean
    public void clearTerminal() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            // Exception handling
            System.out.println("\n\n\n\n\n\n\n\n\n\n"); // fallback method, prints new lines to clear the screen
        }
    }

    public boolean isGameOver() {
        return player1.isHandEmpty() && player2.isHandEmpty();
    }

    public void displayGameState() {
        System.out.println("Player 1's top card on face-up pile: " + topCardToString(player1.getFaceUpPile()));
        System.out.println("Player 2's top card on face-up pile: " + topCardToString(player2.getFaceUpPile()) + "\n");

        System.out.println("Middle piles: ");
        for (int i = 0; i < middlePiles.size(); i++) {
            System.out.println("Middle pile " + (i + 1) + ": " + topCardToString(middlePiles.get(i)));
        }
        System.out.println();
    }

    private String topCardToString(Stack<Card> pile) {
        return pile.isEmpty() ? "Empty" : pile.peek().toString();
    }

}