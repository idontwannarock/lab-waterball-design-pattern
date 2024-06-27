package lab.waterball.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hand {

    private final List<Card> cards;

    Hand() {
        this.cards = new ArrayList<>(13);
    }

    void addCard(Card card) {
        this.cards.add(card);
    }

    boolean hasNoCard() {
        return size() == 0;
    }

    int size() {
        return this.cards.size();
    }

    Card show(int targetIndex) {
        return this.cards.remove(targetIndex);
    }

    String options() {
        return IntStream.range(0, this.cards.size())
                .mapToObj(index -> String.format("(%d) %s", index, this.cards.get(index).toString()))
                .collect(Collectors.joining(" "));
    }
}
