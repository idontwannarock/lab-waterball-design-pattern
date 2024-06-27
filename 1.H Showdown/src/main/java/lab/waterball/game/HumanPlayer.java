package lab.waterball.game;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HumanPlayer extends Player {

    private static final Scanner INPUT = new Scanner(System.in);

    @Override
    void nameHimOrHerself() {
        System.out.print("Input your name: (1-8 characters) ");
        String name = INPUT.next();
        try {
            name(name);
            System.out.println("Player '" + name() + "' is playing here.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            nameHimOrHerself();
        }
    }

    @Override
    boolean chooseToExchangeHand() {
        System.out.println("Your hand: " + hand().options());
        System.out.print("Would you like to exchange hands? (y/n): ");
        String answer = INPUT.next().toLowerCase().strip();
        boolean choice = answer.equals("y");
        if (Boolean.FALSE.equals(choice)) {
            System.out.println("Player '" + name() + "' chose not to exchange hand.");
        }
        return choice;
    }

    @Override
    Player selectTargetToExchange(List<Player> choices) {
        printChoices(choices);
        int targetIndex = INPUT.nextInt();
        if (targetIndex < 0 || targetIndex >= choices.size()) {
            System.out.println("Invalid target. Please choose again.");
            selectTargetToExchange(choices);
        }
        Player target = choices.get(targetIndex);
        System.out.println("Player '" + name() + "' chose to exchange hand with '" + target.name() + "'.");
        return target;
    }

    @Override
    void selectCardToShow() {
        System.out.print("Select a card you would like to show: " + hand().options() + " ");
        int target = INPUT.nextInt();
        if (target < 0 || target >= hand().size()) {
            System.out.println("Invalid target. Please choose again.");
            selectCardToShow();
        }
        decideCardToShow(hand().show(target));
        System.out.println("You chose to show: " + showCard());
    }

    private void printChoices(List<Player> choices) {
        String otherPlayers = IntStream.range(0, choices.size())
                .mapToObj(index -> String.format("(%d) %s", index, choices.get(index).name()))
                .collect(Collectors.joining(", "));
        System.out.print("Select a player you would like to exchange hands: " + otherPlayers + " ");
    }
}
