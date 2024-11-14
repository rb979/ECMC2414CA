import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CardGame {
    private Player[] players;
    private Deck[] decks;

    public static void main(String[] args) {
        CardGame game = new CardGame();
        game.run();
    }

    public CardGame() {
        int numPlayers = getNumPlayers();
        Pack pack = loadPack(numPlayers);

        // Create the decks and players
        decks = new Deck[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            decks[i] = new Deck(i + 1);
        }

        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(i + 1, numPlayers, decks);
        }

        // Deal the cards
        for (int j = 0; j < 4; j ++) {
            for (int i = 0; i < numPlayers; i++) {
                players[i].giveCard(pack.pop());
            }
        }

        for (int j = 0; j < 4; j ++) {
            for (int i = 0; i < numPlayers; i++) {
                decks[i].giveCard(pack.pop());
            }
        }
    }

    public void run() {
        for (Player player : players) {
            player.start();
        }

        boolean gameOver = false;

        while (!gameOver) {
            for (Player player : players) {
                if (player.getHasWon()) {
                    gameOver = true;

                    for (Player loser : players) {
                        if (loser != player) {
                            loser.flagLose(player);
                        }
                    }
                }
            }
        }
    }

    private int getNumPlayers() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of players: ");

        int numPlayers;
        try {
            numPlayers = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid number of players!");
            return getNumPlayers();
        }

        if (numPlayers <= 0) {
            System.out.println("Invalid number of players!");
            return getNumPlayers();
        }

        return numPlayers;
    }

    private String getPackLocation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter pack location: ");

        return sc.next();
    }

    private Pack loadPack(int numPlayers) {
        String fileName = getPackLocation();

        try {
            return new Pack(fileName, numPlayers);
        } catch (IOException e) {
            System.out.println("Invalid pack file.");
            return loadPack(numPlayers);
        }
    }
}
