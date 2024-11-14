import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deck {
    private final IDeckLogger logger = new MockDeckLogger();
    private final int n;
    private volatile ArrayList<Card> deck = new ArrayList<>();

    public Lock lock = new ReentrantLock();

    public Deck(int n) {
        this.n = n;
    }

    public void giveCard(Card card) {
        this.deck.add(card);
    }

    public synchronized Card draw() {
        return deck.removeFirst();
    }

    public synchronized void discard(Card card) {
        deck.add(card);
    }

    public int getN() {
        return n;
    }

    public int getDeckSize() {
        return deck.size();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}
