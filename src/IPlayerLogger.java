public interface IPlayerLogger {
    void logDraw(Player player, Card card, Deck deck);
    void logDiscard(Player player, Card card, Deck deck);
    void logHand(Player player);
}
