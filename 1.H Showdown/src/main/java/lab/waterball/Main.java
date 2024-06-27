package lab.waterball;

import lab.waterball.game.AiPlayer;
import lab.waterball.game.Game;
import lab.waterball.game.HumanPlayer;
import lab.waterball.game.Player;

public class Main {

    public static void main(String[] args) {
        Player first = new HumanPlayer();
        Player second = new AiPlayer();
        Player third = new AiPlayer();
        Player fourth = new AiPlayer();
        new Game(first, second, third, fourth).start();
    }
}
