package lab.waterball.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GameTest {

    private List<Player> players;

    private Throwable actual;

    @Test
    void givenNoPlayer_whenCreateGame_thenThrowsException() {
        // arrange
        givenNoPlayer();

        // act
        startAGame();

        // assert
        Assertions.assertThat(this.actual)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Exactly 4 players are required for a game!");
    }

    @Test
    void givenNotEnoughPlayer_whenCreateGame_thenThrowsException() {
        // arrange
        givenOnePlayer(new HumanPlayer());
        givenOnePlayer(new HumanPlayer());
        givenOnePlayer(new HumanPlayer());

        // act
        startAGame();

        // assert
        Assertions.assertThat(this.actual)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Exactly 4 players are required for a game!");
    }

    @Test
    void givenTooManyPlayers_whenCreateGame_thenThrowsException() {
        // arrange
        givenOnePlayer(new HumanPlayer());
        givenOnePlayer(new HumanPlayer());
        givenOnePlayer(new HumanPlayer());
        givenOnePlayer(new HumanPlayer());
        givenOnePlayer(new HumanPlayer());

        // act
        startAGame();

        // assert
        Assertions.assertThat(this.actual)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Exactly 4 players are required for a game!");
    }

    private void givenNoPlayer() {
        givenOnePlayer(null);
    }

    private void givenOnePlayer(Player player) {
        if (this.players == null) {
            this.players = new ArrayList<>(4);
        }
        if (player != null) {
            this.players.add(player);
        }
    }

    private void startAGame() {
        this.actual = Assertions.catchThrowable(() -> new Game(players.toArray(new Player[0])).start());
    }
}
