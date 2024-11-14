import java.util.ArrayList;

public class Player {
    private IPlayerLogger logger;
    private ArrayList<Card> cards;

    private Deck leftDeck;
    private Deck rightDeck;

    /**
     * Constructs a player.
     *
     * @param logger    an {@link IPlayerLogger} implementation to log with
     * @param leftDeck  the deck to take from
     * @param rightDeck the deck to give to
     * @param cards     the player's initial hand
     */
    public Player(IPlayerLogger logger, Deck leftDeck, Deck rightDeck, ArrayList<Card> cards) {
        this.logger = logger;
        this.cards = cards;
        this.leftDeck = leftDeck;
        this.rightDeck = rightDeck;
    }

    public void give_card(Card card) {
        this.cards.add(card);
    }

    public void run() throws InterruptedException {

    }
}
