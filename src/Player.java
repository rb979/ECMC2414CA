import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Represents a Player in a card game. This Player extends the `Thread` class
 * and runs concurrently with other Players.
 */
public class Player extends Thread {

    /**
     * This Player's number (1-indexed).
     */
    private final int n;

    private final IPlayerLogger logger;
    private final ArrayList<Card> cards;

    private final Deck leftDeck;
    private final Deck rightDeck;

    private boolean hasWon = false;
    private boolean hasLost = false;

    private final Random rand = new Random();

    /**
     * Constructs a Player object.
     *
     * @param logger the {@link IPlayerLogger} to use
     * @param n          this player's number (1-indexed)
     * @param numPlayers the number of players in the game
     * @param decks      an ordered array of {@link Deck Decks} being used
     * @throws IllegalArgumentException if the provided number of players is less than 2
     */
    public Player(IPlayerLogger logger, int n, int numPlayers, Deck[] decks) {
        this.logger = logger;
        this.n = n;
        this.cards = new ArrayList<>();
        this.leftDeck = decks[(n - 1) % numPlayers];
        this.rightDeck = decks[n % numPlayers];
    }

    /**
     * Adds a {@link Card} to this Player's hand.
     *
     * @param card the Card to be added
     */
    public void giveCard(Card card) {
        this.cards.add(card);
    }

    /**
     * The main execution loop for this Player thread. This method continues
     * to run until the Player wins or loses the game.
     */
    @Override
    public void run() {
        while (!hasWon && !hasLost) {
            if (leftDeck.getDeckSize() > 2) {
                doTurn();
            }

            hasWon = hasWon();

            try {
                Thread.sleep(rand.nextLong(10, 50));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (hasWon) {
            logger.logWin(this);
        }
    }

    /**
     * Checks if all cards in this Player's hand have the same denomination. If so, the Player has won.
     */
    private boolean hasWon() {
        boolean allSame = true;

        int d = cards.getFirst().getDenomination();

        for (Card card : cards) {
            if (card.getDenomination() != d) {
                allSame = false;
                break;
            }
        }

        return allSame;
    }

    /**
     * Performs a single turn for this Player. This involves drawing a card from the left deck and discarding a non-matching card to the right deck.
     */
    private void doTurn() {
        leftDeck.l.lock();
        Card draw = leftDeck.draw();
        leftDeck.l.unlock();

        cards.add(draw);

        logger.logDraw(this, draw, leftDeck);

        Collections.shuffle(cards);

        for (Card card : cards) {
            if (card.getDenomination() != n) {
                cards.remove(card);

                rightDeck.l.lock();
                rightDeck.discard(card);
                rightDeck.l.unlock();

                logger.logDiscard(this, card, rightDeck);

                break;
            }
        }
    }

    /**
     * Sets the {@code hasLost} flag for this Player and logs the winner.
     *
     * @param winner the Player who won the game
     */
    public void flagLose(Player winner) {
        hasLost = true;
        logger.logLose(this, winner);
    }

    /**
     * Returns whether this Player has won the game.
     *
     * @return {@code true} if the Player has won, {@code false} otherwise
     */
    public synchronized boolean getHasWon() {
        return hasWon;
    }

    /**
     * Returns this Player's number.
     *
     * @return the Player's number
     */
    public int getN() {
        return n;
    }

    /**
     * Returns a copy of the {@link ArrayList} containing this Player's {@link Card Cards}.
     *
     * @return a copy of the Player's card list
     */
    public ArrayList<Card> getCards() {
        return new ArrayList<>(cards);
    }
}