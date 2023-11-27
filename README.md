# Donkey - A Strategic Card Game Where Skill Meets Luck

Donkey is a dynamic card game developed in Java, offering a blend of strategy and luck. The game involves two players competing to strategically play cards from their hands and face-up piles onto their own or their opponent's pile, as well as onto central middle piles. The aim is to empty one's hand and face-up pile before the opponent.

## How to Play

### Setup

- Initialization: The game starts with a deck of 52 standard playing cards. Each card has a suit (Hearts, Diamonds, Clubs, Spades) and a value (ranging from 1 to 13).
- Dealing Cards: The deck is shuffled, and cards are distributed evenly between two players. Each player has their own hand of cards and a face-up pile (initially empty).

### Turn Mechanics

- Player's Turn: On their turn, a player has two main choices:
  - Draw a Card: The player draws the top card from the deck.
  - Play the Top Card: The player plays the top card from their face-up pile.
- Playing a Card: When playing a card, the player can choose to place it on:
  - Their Own Face-Up Pile: If the pile is empty or the card's value is exactly one more than the top card's value.
  - The Opponent's Face-Up Pile: Under the same conditions as playing on their own pile.
  - One of the Middle Piles: Each middle pile corresponds to one suit. A card can be played on a middle pile if it matches the pile's suit and is the next sequential card (starting with the Ace).

### Additional Rules

- Empty Hand: If a player's hand is empty, they must flip their face-up pile over to create a new hand. This is done only when the player needs to draw a card, and their hand is empty.
- No Playable Card: If a player cannot play their drawn card on any pile, they must retain the card in their hand.

### Winning the Game

- The game is won by the first player who empties both their hand and their face-up pile.

### Strategy Tips

- Balancing Acts: Players should balance between reducing their hand and strategically playing cards to their face-up pile and middle piles.
- Watching the Opponent: Keep an eye on the opponent's face-up pile to predict their next moves and potentially block them.
- Remember, this is a game of patterns and sequences!

## How the Program Works

### Key Components

#### 1. `Card` Class

Represents a playing card with a suit and value. It also includes a custom `toString` method for display.

```java
public class Card {
    private String suit;
    private int value;

    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        // Conversion logic for displaying cards
    }
}
```

#### 2. `Deck` Class

Models a deck of cards with methods to initialize, shuffle, and draw cards.

```java
public class Deck {
    private Stack<Card> cards;

    public Deck() {
        this.cards = new Stack<>();
        initializeDeck();
    }

    private void initializeDeck() {
        // Deck initialization logic
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.isEmpty() ? null : cards.pop();
    }

    // Other utility methods
}
```

#### 3. `Player` Class

Represents a player with methods for handling their hand and face-up pile.

```java
public class Player {
    private Stack<Card> hand;
    private Stack<Card> faceUpPile;

    public Player() {
        this.hand = new Stack<>();
        this.faceUpPile = new Stack<>();
    }

    public void drawFromDeck(Deck deck) {
        // Drawing card logic
    }

    public void playCardOnFaceUpPile(Card card, boolean isOwnPile) {
        // Playing card logic
    }

    public void flipFaceUpPile() {
        // Flipping pile logic
    }

    // Other methods
}
```

#### 4. `Game` Class

Manages the game flow, including setup, player turns, and game-over checks.

```java
public class Game {
    private Player player1;
    private Player player2;
    private Deck deck;
    private List<Stack<Card>> middlePiles;

    public Game() {
        // Initialization logic
    }

    public void startGame() {
        // Game loop logic
    }

    private void takeTurn(Player player, Scanner scanner) {
        // Turn-taking logic
    }

    private boolean canPlayOnPlayerPile(Card card, Player player) {
        // Logic for playing on opponent's pile
    }

    private boolean canPlayOnMiddlePile(Card card) {
        // Logic for playing on middle pile
    }

    public void clearTerminal() {
        // Terminal clearing logic
    }

    public boolean isGameOver() {
        // Game-over check logic
    }

    private void displayGameState() {
        // Game state display logic
    }
}
```

#### 5. `App` Class

The entry point of the program, creating a game instance and starting it.

```java
public class App {
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
```
