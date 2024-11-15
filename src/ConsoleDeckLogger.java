/**
 * Logs Deck actions to console.
 */
public class ConsoleDeckLogger implements IDeckLogger {
    @Override
    public void logCards(Deck deck) {
        StringBuilder cards = new StringBuilder();

        for (Card c : deck.getDeck()) {
            cards.append(c.getDenomination());
            cards.append(" ");
        }

        System.out.printf("deck %d contains: %s", deck.getN(), cards);
    }
}
