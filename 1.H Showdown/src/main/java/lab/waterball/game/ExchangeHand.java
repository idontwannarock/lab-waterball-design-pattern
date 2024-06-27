package lab.waterball.game;

public class ExchangeHand {

    private final Player exchanger;
    private final Player target;

    private int turn = 3;

    ExchangeHand(Player exchanger, Player target) {
        exchangeHand(exchanger, target);
        this.exchanger = exchanger;
        this.target = target;
    }

    void exchange() {
        this.turn--;
        if (this.turn == 0) {
            exchangeHand(exchanger, target);
            System.out.println("'" + exchanger.name() + "' and '" + target.name() + "' exchanged back their hands.");
        }
    }

    private void exchangeHand(Player source, Player target) {
        Hand sourceHand = source.hand();
        source.setHand(target.hand());
        target.setHand(sourceHand);
    }
}
