import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents a Deck of cards in the game. The Deck holds and manages a collection
 * of {@link Card} objects, allowing cards to be drawn or discarded by players.
 * This class supports thread-safe operations using a {@link ReentrantLock}.
 */
public class Deck {

    private final IDeckLogger logger;
    private final int n;
    private final ArrayList<Card> deck = new ArrayList<>();

    public ReentrantLock l = new ReentrantLock();

    /**
     * Constructs a {@code Deck} for use in the game.
     *
     * @param logger the {@link IDeckLogger} to use
     * @param n the identifier for this Deck, typically representing the player's number
     */
    public Deck(IDeckLogger logger, int n) {
        this.logger = logger;
        this.n = n;
    }

    /**
     * Adds a {@link Card} to the Deck.
     *
     * <p>
     * This method adds the specified card to the end of the Deck.
     * </p>
     *
     * @param card the Card to be added to the Deck
     */
    public void giveCard(Card card) {
        this.deck.add(card);
    }

    /**
     * Draws and removes the first {@link Card} from the Deck.
     *
     * <p>
     * This method is synchronized to ensure thread-safe access to the Deck.
     * </p>
     *
     * @return the {@link Card} that was drawn from the Deck
     */
    public Card draw() {
        return deck.removeFirst();
    }

    /**
     * Adds a {@link Card} to the Deck, placing it at the end.
     *
     * <p>
     * This method allows for discarding cards back into the Deck.
     * </p>
     *
     * @param card the Card to be discarded into the Deck
     */
    public void discard(Card card) {
        deck.add(card);
    }

    /**
     * Returns the identifier for this Deck.
     *
     * @return the identifier of this Deck
     */
    public int getN() {
        return n;
    }

    /**
     * Returns the current size of the Deck (number of cards remaining).
     *
     * @return the size of the Deck
     */
    public synchronized int getDeckSize() {
        return deck.size();
    }

    /**
     * Returns the {@link Card Cards} in the Deck.
     *
     * @return the {@code Cards} in the decDeck
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Logs the current state of this Deck's cards.
     */
    public void logCards() {
        logger.logCards(this);
    }
}
