package test;

import main.Card;
import main.CardHolder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CardHolderTest {

    private CardHolder cardHolderA;
    private CardHolder cardHolderB;

    @Before
    public void setUp() {
        cardHolderA = new CardHolder(1) {
        };
        cardHolderB = new CardHolder(2) {
        };
    }

    @Test
    public void pushCard() {
        Card card = new Card(1);
        cardHolderA.pushCard(card);

        List<Card> cards = cardHolderA.getCards();
        assertEquals(1, cards.size());
        assertEquals(card, cards.getFirst());
    }

    @Test
    public void popCard() {
        Card card = new Card(1);
        cardHolderA.pushCard(card);

        Card poppedCard = cardHolderA.popCard();
        assertEquals(card, poppedCard);
        assertTrue(cardHolderA.getCards().isEmpty());
    }

    @Test(expected = IllegalStateException.class)
    public void popCard_empty() {
        cardHolderA.popCard();
    }

    @Test
    public void removeCard() {
        Card card = new Card(1);
        cardHolderA.pushCard(card);

        boolean removed = cardHolderA.removeCard(card);
        assertTrue(removed);
        assertTrue(cardHolderA.getCards().isEmpty());
    }

    @Test
    public void removeCard_nonExistent() {
        Card card = new Card(1);
        boolean removed = cardHolderA.removeCard(card);

        assertFalse(removed);
    }

    @Test
    public void transferCard() {
        Card card = new Card(1);
        cardHolderA.pushCard(card);

        cardHolderA.transferCard(card, cardHolderB);

        assertTrue(cardHolderA.getCards().isEmpty());
        assertEquals(1, cardHolderB.getCards().size());
        assertEquals(card, cardHolderB.getCards().getFirst());
    }

    @Test(expected = IllegalStateException.class)
    public void transferCard_nonExistent() {
        Card card = new Card(1);
        cardHolderA.transferCard(card, cardHolderB);
    }

    @Test
    public void allCardsSame() {
        cardHolderA.pushCard(new Card(1));
        cardHolderA.pushCard(new Card(1));

        assertTrue(cardHolderA.allCardsSame());

        cardHolderA.pushCard(new Card(2));
        assertFalse(cardHolderA.allCardsSame());
    }

    @Test
    public void allCardsSame_empty() {
        assertTrue(cardHolderA.allCardsSame());
    }

    @Test
    public void getCards() {
        Card card1 = new Card(1);
        Card card2 = new Card(2);

        cardHolderA.pushCard(card1);
        cardHolderA.pushCard(card2);

        List<Card> cards = cardHolderA.getCards();
        assertEquals(2, cards.size());
        assertTrue(cards.contains(card1));
        assertTrue(cards.contains(card2));
    }

    @Test
    public void size() {
        assertEquals(0, cardHolderA.size());

        cardHolderA.pushCard(new Card(1));
        cardHolderA.pushCard(new Card(2));

        assertEquals(2, cardHolderA.size());
    }
}
