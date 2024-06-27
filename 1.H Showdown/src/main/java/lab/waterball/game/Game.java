package lab.waterball.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private static final int TOTAL_ROUNDS = 13;

    private final List<Player> players;

    private final Deck deck = new Deck();

    private final List<ExchangeHand> exchangeHands = new ArrayList<>(4);

    public Game(Player... playersToJoin) {
        this.players = joinPlayers(playersToJoin);
    }

    public void start() {
        nameThemselves();
        shuffle();
        drawHands();
        playRounds();
        showWinner();
    }

    private List<Player> joinPlayers(Player[] playersToJoin) {
        if (playersToJoin == null || playersToJoin.length != 4) {
            throw new IllegalArgumentException("Exactly 4 players are required for a game!");
        }
        return List.of(playersToJoin);
    }

    private void nameThemselves() {
        this.players.forEach(Player::nameHimOrHerself);
    }

    private void shuffle() {
        this.deck.shuffle();
    }

    private void drawHands() {
        while (this.deck.hasCard()) {
            this.players.forEach(player -> player.drawCard(this.deck));
        }
    }

    private void playRounds() {
        for (int round = 1; round <= TOTAL_ROUNDS; round++) {
            this.exchangeHands.forEach(ExchangeHand::exchange);
            this.players.forEach(player -> {
                player.exchangeHand(this.players).ifPresent(this.exchangeHands::add);
                player.show();
            });
            showdown();
        }
    }

    private void showdown() {
        printShowedCards();
        this.players.stream()
                .filter(player -> player.showCard() != null)
                .max(Comparator.comparing(Player::showCard))
                .ifPresent(winner -> {
                    System.out.println("The winner of the round is '" + winner.name() + "'!");
                    winner.addPoint();
                });
    }

    private void printShowedCards() {
        String names = this.players.stream()
                .map(Player::name)
                .map(name -> String.format("%9s ", name))
                .collect(Collectors.joining("|"));
        String cards = this.players.stream()
                .map(Player::showCard)
                .map(card -> card == null ? "" : card.toString())
                .map(card -> String.format("%9s ", card))
                .collect(Collectors.joining("|"));
        System.out.println("Showed cards:");
        System.out.println("|" + names + "|");
        System.out.println("|" + cards + "|");
    }

    private void showWinner() {
        Player winner = Collections.max(this.players, Comparator.comparingInt(Player::point));
        System.out.println("The winner of the game is '" + winner.name() + "'!");
    }
}
