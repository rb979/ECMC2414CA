import java.util.ArrayList;

public class Player {
    private IPlayerLogger logger;
    private ArrayList<Card> cards;

    private Deck leftDeck;
    private Deck rightDeck;

    public Player(IPlayerLogger logger, Deck leftDeck, Deck rightDeck, ArrayList<Card> cards) {
        this.logger = logger;
        this.cards = cards;
        this.leftDeck = leftDeck;
        this.rightDeck = rightDeck;
    }
}
