import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class Pack {
    ArrayList<Card> cards;

    /**
     * Reads a Pack from a pack file.
     *
     * @param fileName the name of the file to read
     * @param numPlayers the number of players in the game
     * @throws IOException if the pack file is invalid
     * */
    public Pack(String fileName, int numPlayers) throws IOException {
        ArrayList<Card> card_input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line);
                    card_input.add(new Card(number));
                } catch (NumberFormatException e) {
                    throw new IOException(e.getMessage());
                }
            }
        }

        if (card_input.size() != 8 * numPlayers) {
            throw new IOException("Invalid number of lines in input file");
        }

        // TODO remove
        Collections.shuffle(card_input);

        this.cards = card_input;
    }

    public Card pop() throws NoSuchElementException {
        return cards.removeFirst();
    }
}
