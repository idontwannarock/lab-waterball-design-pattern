package lab.waterball.game;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class AiPlayer extends Player {

    @Override
    void nameHimOrHerself() {
        name("AI-" + UUID.randomUUID().toString().substring(0, 5));
        System.out.println("Player '" + name() + "' is playing here.");
    }

    @Override
    boolean chooseToExchangeHand() {
        boolean choice = new Random().nextBoolean();
        if (Boolean.FALSE.equals(choice)) {
            System.out.println("Player '" + name() + "' chose NOT to exchange hand.");
        }
        return choice;
    }

    @Override
    Player selectTargetToExchange(List<Player> choices) {
        var random = new Random();
        int targetIndex = random.nextInt(0, choices.size());
        Player target = choices.get(targetIndex);
        System.out.println("Player '" + name() + "' chose to exchange hand with '" + target.name() + "'.");
        return target;
    }

    @Override
    void selectCardToShow() {
        var random = new Random();
        int target = random.nextInt(0, hand().size());
        decideCardToShow(hand().show(target));
    }
}
