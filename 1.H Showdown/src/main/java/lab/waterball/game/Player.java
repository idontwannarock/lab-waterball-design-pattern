package lab.waterball.game;

import java.util.List;
import java.util.Optional;

public abstract class Player {

    private Hand hand = new Hand();
    private int point = 0;
    private boolean hasExchangedHand = false;

    private String name;
    private Card showCard;

    Hand hand() {
        return hand;
    }

    void setHand(Hand hand) {
        if (hand == null || hand.hasNoCard()) {
            throw new IllegalArgumentException("Hand must have at least one card");
        }
        this.hand = hand;
    }

    void drawCard(Deck deck) {
        this.hand.addCard(deck.draw());
    }

    int point() {
        return this.point;
    }

    void addPoint() {
        this.point++;
    }

    boolean hasNotExchangedHand() {
        return !this.hasExchangedHand;
    }

    void handExchanged() {
        this.hasExchangedHand = true;
    }

    String name() {
        return this.name;
    }

    void name(String name) {
        if (name == null || name.isBlank() || name.strip().length() > 8) {
            throw new IllegalArgumentException("Name must have at least one to at most 8 characters.");
        }
        this.name = name;
    }

    Card showCard() {
        return this.showCard;
    }

    void decideCardToShow(Card card) {
        this.showCard = card;
    }

    Optional<ExchangeHand> exchangeHand(List<Player> players) {
        if (isAllowedToExchangeHand() && chooseToExchangeHand()) {
            List<Player> choices = getChoices(players);
            Player target = selectTargetToExchange(choices);
            handExchanged();
            return Optional.of(new ExchangeHand(this, target));
        }
        return Optional.empty();
    }

    private boolean isAllowedToExchangeHand() {
        return hasNotExchangedHand();
    }

    private List<Player> getChoices(List<Player> players) {
        return players.stream()
                .filter(player -> !player.name().equals(this.name()))
                .toList();
    }

    void show() {
        if (hand().hasNoCard()) {
            decideCardToShow(null);
        } else {
            selectCardToShow();
        }
    }

    abstract void nameHimOrHerself();

    abstract boolean chooseToExchangeHand();

    abstract Player selectTargetToExchange(List<Player> players);

    abstract void selectCardToShow();
}
