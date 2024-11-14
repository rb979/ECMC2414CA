public class MockPlayerLogger implements IPlayerLogger {
    @Override
    public void logDraw(Player player, Card card, Deck deck) {
        StringBuilder cardString = new StringBuilder();

        for (Card c : player.getCards()) {
            cardString.append(c.getDenomination());
            cardString.append(" ");
        }

        System.out.printf("player %d draws a %d from deck %d: %s\n", player.getN(), card.getDenomination(), deck.getN(), cardString);
    }

    @Override
    public void logDiscard(Player player, Card card, Deck deck) {
        StringBuilder cardString = new StringBuilder();

        for (Card c : deck.getDeck()) {
            cardString.append(c.getDenomination());
            cardString.append(" ");
        }

        System.out.printf("player %d discards a %d to deck %d: %s\n", player.getN(), card.getDenomination(), deck.getN(), cardString);
    }

    @Override
    public void logHand(Player player) {
        System.out.printf("player %d's current hand is: %s\n", player.getN(), player.getCards().toString());
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
