/**
 * Logs Player actions to console.
 */
public class ConsolePlayerLogger implements IPlayerLogger {
    @Override
    public void logDraw(Player player, Card card, Deck deck) {
        System.out.printf("player %d draws a %d from deck %d\n", player.getN(), card.getDenomination(), deck.getN());
    }

    @Override
    public void logDiscard(Player player, Card card, Deck deck) {
        System.out.printf("player %d discards a %d to deck %d\n", player.getN(), card.getDenomination(), deck.getN());
    }

    @Override
    public void logHand(Player player) {
        StringBuilder cards = new StringBuilder();

        for (Card c : player.getCards()) {
            cards.append(c.getDenomination());
            cards.append(" ");
        }

        System.out.printf("player %d's current hand is: %s\n", player.getN(), cards);
    }

    @Override
    public void logWin(Player player) {
        System.out.printf("player %d wins\n", player.getN());
    }

    @Override
    public void logLose(Player player, Player winner) {
        System.out.printf("player %d has informed player %d that they have won\n", winner.getN(), player.getN());
    }
}
