package card;

public class Card implements Comparable<Card> {
    public Figure figure;
    public Suite suite;

    public Card(String cardDescription) throws Exception {
        this.figure = Figure.getFigureFromInitial(cardDescription.substring(0, 1));
        this.suite = Suite.getSuiteFromInitial(cardDescription.substring(1));
    }

    @Override
    public int compareTo(Card otherCard) {
        return Integer.compare(this.figure.value, otherCard.figure.value);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Card cardToCheck)) {
            return false;
        }

        return this.figure == cardToCheck.figure && this.suite == cardToCheck.suite;
    }

    public enum Figure {
        TWO("2", 2, "2"),
        THREE("3", 3, "3"),
        FOUR("4", 4, "4"),
        FIVE("5", 5, "5"),
        SIX("6", 6, "6"),
        SEVEN("7", 7, "7"),
        EIGHT("8", 8, "8"),
        NINE("9", 9, "9"),
        TEN("T", 10, "10"),
        JACK("J", 11, "Jack"),
        QUEEN("Q", 12, "Queen"),
        KING("K", 13, "King"),
        ACE("A", 14, "Ace");

        public final String initial;
        public final int value;
        public final String label;

        Figure(String initial, int value, String label) {
            this.initial = initial;
            this.value = value;
            this.label = label;
        }

        public static Figure getFigureFromInitial(String initial) throws Exception {
            for (Figure figure : Figure.values()) {
                if (initial.equals(figure.initial)) {
                    return figure;
                }
            }
            throw new Exception("Expected value to be one of [2-9|T|J|Q|K], got " + initial);
        }
    }

    public enum Suite {
        CLUB("C","Club"),
        DIAMOND("D", "Diamond"),
        HEART("H", "Heart"),
        SPADE("S", "Spade");

        public final String initial;
        public final String label;

        Suite(String initial, String label) {
            this.initial = initial;
            this.label = label;
        }

        public static Suite getSuiteFromInitial(String initial) throws Exception {
            for (Suite suite : Suite.values()) {
                if (initial.equals(suite.initial)) {
                    return suite;
                }
            }
            throw new Exception("Unknown suite: " + initial);
        }
    }
}
