package lab.waterball.game;

import java.util.*;

public class Deck {

    private final Deque<Card> cards;

    Deck() {
        this.cards = new ArrayDeque<>(52);
        for (int rank = Card.Rank.TWO.order(); rank <= Card.Rank.ACE.order(); rank++) {
            for (int suit = Card.Suit.CLUB.order(); suit <= Card.Suit.SPADE.order(); suit++) {
                this.cards.push(new Card(rank, suit));
            }
        }
    }

    void shuffle() {
        List<Card> cards = new ArrayList<>(this.cards);
        Collections.shuffle(cards);
        this.cards.clear();
        cards.forEach(this.cards::push);
    }

    boolean hasCard() {
        return !this.cards.isEmpty();
    }

    Card draw() {
        return this.cards.pop();
    }
}
