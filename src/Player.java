import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player extends Thread {
    private final int n;

    private final IPlayerLogger logger = new MockPlayerLogger();
    private final ArrayList<Card> cards;

    private final Deck leftDeck;
    private final Deck rightDeck;

    private boolean hasWon;

    /**
     * Constructs a player.
     *
     * @param n this player's number
     * @param numPlayers the number of players in the game
     * @param decks the decks
     */
    public Player(int n, int numPlayers, Deck[] decks) {
        this.n = n;
        this.cards = new ArrayList<>();
        this.leftDeck = decks[(n - 1) % numPlayers];
        this.rightDeck = decks[n % numPlayers];
    }

    public void giveCard(Card card) {
        this.cards.add(card);
    }

    @Override
    public void run() {
        while (!hasWon) {
            while(leftDeck.isLocked() || rightDeck.isLocked()) {
                try {
                    Thread.sleep(new Random().nextInt(500) + 5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            leftDeck.lock();
            rightDeck.lock();

            Card draw = leftDeck.draw();
            cards.add(draw);

            logger.logDraw(this, draw, leftDeck);

            Collections.shuffle(cards);

            for (Card card : cards) {
                if (card.getDenomination() != n) {
                    cards.remove(card);
                    rightDeck.discard(card);

                    logger.logDiscard(this, card, rightDeck);

                    break;
                }
            }

            leftDeck.unlock();
            rightDeck.unlock();

            boolean allSame = true;

            int prev = cards.getFirst().getDenomination();
            for (Card card : cards) {
                if (card.getDenomination() != prev) {
                    allSame = false;
                    break;
                }
            }

            if (allSame) {
                hasWon = true;
            }

            try {
                Thread.sleep(new Random().nextInt(500) + 5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        logger.logWin(this);
    }

    public void flagLose() {
        logger.logLose(this, this);
    }

    public synchronized boolean getHasWon() {
        return hasWon;
    }

    public int getN() {
        return n;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
