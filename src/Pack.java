import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Represents the Pack of cards used in the game.
 */
public class Pack {
    private final ArrayList<Card> cards;

    /**
     * Creates a {@code Pack} by reading cards from a file.
     *
     * <p>
     * This method throws an {@link IOException} if:
     * <ul>
     *     <li>The file is invalid.</li>
     *     <li>The file does not contain exactly {@code 8 * numPlayers} lines.</li>
     *     <li>The file contains anything other than valid integer values representing card numbers.</li>
     * </ul>
     * </p>
     *
     * @param fileName   the name of the file containing card data
     * @param numPlayers the number of players in the game (used to validate file content)
     * @throws IOException if the pack file is invalid
     */
    public Pack(String fileName, int numPlayers) throws IOException {
        ArrayList<Card> card_input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line);
                    card_input.add(new Card(number));
                } catch (NumberFormatException e) {
                    throw new IOException("must only contain integers");
                }
            }
        }

        if (card_input.size() != 8 * numPlayers) {
            throw new IOException("must contain 8n lines");
        }

        Collections.shuffle(card_input);

        this.cards = card_input;
    }

    /**
     * Removes and returns the first {@link Card} from the Pack.
     *
     * <p>
     * This method throws a {@link NoSuchElementException} if the Pack is empty.
     * </p>
     *
     * @return the first card in the Pack
     * @throws NoSuchElementException if the Pack is empty
     */
    public Card pop() throws NoSuchElementException {
        return cards.removeFirst();
    }
}