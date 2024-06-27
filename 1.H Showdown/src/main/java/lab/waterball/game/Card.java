package lab.waterball.game;

public class Card implements Comparable<Card> {

    private final Rank rank;
    private final Suit suit;

    Card(int rankOrder, int suitOrder) {
        this.rank = Rank.of(rankOrder);
        this.suit = Suit.of(suitOrder);
    }

    @Override
    public String toString() {
        return this.rank.toString() + this.suit.toString();
    }

    @Override
    public int compareTo(Card other) {
        return this.rank.compare(other.rank) == 0
                ? this.suit.compare(other.suit)
                : this.rank.compare(other.rank);
    }

    enum Rank {
        TWO(2, " 2"),
        THREE(3, " 3"),
        FOUR(4, " 4"),
        FIVE(5, " 5"),
        SIX(6, " 6"),
        SEVEN(7, " 7"),
        EIGHT(8, " 8"),
        NINE(9, " 9"),
        TEN(10, "10"),
        JACK(11, " J"),
        QUEEN(12, " Q"),
        KING(13, " K"),
        ACE(14, " A");

        private final int order;
        private final String display;

        Rank(int order, String display) {
            this.order = order;
            this.display = display;
        }

        static Rank of(int order) {
            for (Rank rank : Rank.values()) {
                if (rank.order == order) {
                    return rank;
                }
            }
            return null;
        }

        int order() {
            return this.order;
        }

        int compare(Rank other) {
            return Integer.compare(this.order(), other.order());
        }

        @Override
        public String toString() {
            return this.display;
        }
    }

    enum Suit {
        CLUB(1, "♣"),
        DIAMOND(2, "♦"),
        HEART(3, "♥"),
        SPADE(4, "♠");

        private final int order;
        private final String display;

        Suit(int order, String display) {
            this.order = order;
            this.display = display;
        }

        static Suit of(int order) {
            for (Suit suit : Suit.values()) {
                if (suit.order == order) {
                    return suit;
                }
            }
            return null;
        }

        int order() {
            return this.order;
        }

        int compare(Suit other) {
            return Integer.compare(this.order(), other.order());
        }

        @Override
        public String toString() {
            return this.display;
        }
    }
}
