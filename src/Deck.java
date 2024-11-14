import java.util.ArrayList;

public class Deck {
    private final IDeckLogger logger = new MockDeckLogger();
    private final int n;
    private final ArrayList<Card> deck = new ArrayList<>();

    private boolean isLocked = false;

    public Deck(int n) {
        this.n = n;
    }

    public synchronized void lock() {
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
    }

    public synchronized boolean isLocked() {
        return isLocked;
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
