import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Pack {
    ArrayList<Card> cards;

    /**
     * Reads a Pack from a pack file.
     *
     * @param fileName the name of the file to read
     * @throws IOException if the pack file is invalid
     * */
    public Pack(String fileName) throws IOException {
        throw new RuntimeException("Not implemented");
    }

    public Card pop() throws NoSuchElementException {
        return cards.removeFirst();
    }
}
