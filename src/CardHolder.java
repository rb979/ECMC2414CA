import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a CardHolder, a thread-safe collection of {@link Card Cards}.
 */
public abstract class CardHolder {
    protected final int n;
    protected final ArrayList<Card> cards = new ArrayList<>();

    /**
     * Constructs a CardHolder object with the specified identification number.
     *
     * @param n the identification number (1-indexed)
     */
    public CardHolder(int n) {
        this.n = n;
    }

    /**
     * Adds a {@link Card} to this CardHolder.
     *
     * @param card the {@link Card} to be added
     */
    public synchronized void pushCard(Card card) {
        cards.add(card);
    }

    /**
     * Removes and returns the first {@link Card} from this CardHolder.
     *
     * @return the {@link Card} that was drawn from the Deck
     */
    public synchronized Card popCard() {
        return cards.removeFirst();
    }

    /**
     * Removes a specified {@link Card} from this CardHolder.
     *
     * @param card the {@link Card} to remove
     * @return {@code true} if the card was successfully removed, {@code false} otherwise
     */
    public synchronized boolean removeCard(Card card) {
        return cards.remove(card);
    }

    /**
     * Removes a {@link Card} from this CardHolder and adds it to another CardHolder.
     *
     * @param card the {@link Card} to remove
     * @param to   the {@link CardHolder} to transfer the card to
     * @throws IllegalStateException if the card cannot be removed from this CardHolder
     */
    public synchronized void transferCard(Card card, CardHolder to) {
        if (!removeCard(card)) {
            throw new IllegalStateException("invalid card");
        }

        to.pushCard(card);
    }

    /**
     * Checks if all the {@link Card Cards} in this CardHolder have the same denomination.
     *
     * @return {@code true} if all {@link Card Cards} have the same denomination, {@code false} otherwise
     */
    public synchronized boolean allCardsSame() {
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
     * Shuffles the {@link Card Cards} in this CardHolder.
     */
    public synchronized void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Returns an immutable list of the {@link Card Cards} currently held by this CardHolder.
     *
     * @return the {@link ArrayList} of {@link Card Cards}
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    /**
     * Returns the number of {@link Card Cards} in this CardHolder.
     *
     * @return the number of {@link Card Cards} in this CardHolder
     */
    public int size() {
        return cards.size();
    }

    /**
     * Returns the number of this CardHolder.
     *
     * @return the number of this CardHolder
     */
    public int number() {
        return n;
    }
}
