import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final int n;
    private ArrayList<Card> deck;

    public Deck(IDeckLogger logger, ArrayList<Card> cards, int n) {
        this.n = n;
        this.deck = cards;
    }

    public synchronized Card draw() {
        return deck.removeLast();
    }

    public synchronized void discard(Card card) {
        deck.add(card);
    }

    /**
     * Writes the contents of this deck to <code>output/deck[n]_output.txt</code>.
     */
    public synchronized void writeContents() {

    }
}
