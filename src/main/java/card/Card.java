package card;

import java.util.Arrays;
import java.util.Objects;

public class Card implements Comparable<Card> {
    public Figure figure; //FIXME: private + final
    public Suite suite;

    public Card(String cardDescription) throws Exception {
        // FIXME : smell de constructeur qui throw une exception => factory method
        this.figure = Figure.from(cardDescription.substring(0, 1));
        this.suite = Suite.from(cardDescription.substring(1));
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

    @Override
    public int hashCode() {
        return Objects.hash(figure, suite);
    }

    public boolean isPairWith(Card other) {
        return this.figure == other.figure;
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

        public static Figure from(String initial) throws Exception {
            return Arrays.stream(Figure.values())
                .filter(e -> e.initial.equals(initial))
                .findFirst()
                .orElseThrow(() -> new Exception("Expected value to be one of [2-9|T|J|Q|K], got " + initial));
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

        public static Suite from(String initial) throws Exception {
            for (Suite suite : Suite.values()) {
                if (initial.equals(suite.initial)) {
                    return suite;
                }
            }
            throw new Exception("Unknown suite: " + initial);
        }
    }
}
