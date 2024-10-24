import java.io.IOException;
import java.util.ArrayList;

public class CardGame {
    private Player[] players;
    private Deck[] decks;

    public static void main(String[] args) {
        CardGame game = new CardGame();
    }

    public CardGame() {
        int numPlayers = getNumPlayers();
        String packLocation = getPackLocation();
        Pack pack = loadPack(packLocation);

        // TODO need to deal cards

        decks = new Deck[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            decks[i] = new Deck(new DeckLogger(), new ArrayList<>(), i + 1);
        }

        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(new PlayerLogger(), decks[i - 1 % numPlayers], decks[i + 1 % numPlayers], new ArrayList<>());
        }
    }

    private int getNumPlayers() {
        throw new RuntimeException("Not implemented yet");
    }

    private Pack loadPack(String fileName) {
        try {
            return new Pack(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getPackLocation() {
        throw new RuntimeException("Not implemented yet");
    }
}
