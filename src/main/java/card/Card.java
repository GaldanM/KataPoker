package card;

public class Card implements Comparable<Card> {
    public int value;
    public Suite suite;

    public Card(String cardDescription) throws Exception {
        this.value = parseValue(cardDescription.substring(0, 1));
        this.suite = this.parseSuite(cardDescription.substring(1));
    }

    private int parseValue(String valueChar) throws Exception {
        switch (valueChar) {
            case "T":
                return 10;
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
        }

        try {
            int value = Integer.parseInt(valueChar);

            if (value < 2) {
                throw new Exception();
            }

            return value;
        } catch (Exception exception) {
            throw new Exception("Expected value to be one of [2-9|T|J|Q|K], got " + valueChar);
        }
    }

    private Suite parseSuite(String suiteChar) throws Exception {
        return switch (suiteChar) {
            case "C" -> Suite.CLUB;
            case "D" -> Suite.DIAMOND;
            case "H" -> Suite.HEART;
            case "S" -> Suite.SPADE;
            default -> throw new Exception("Unknown suite: " + suiteChar);
        };
    }

    public String valueToString() {
        return switch (this.value) {
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            case 14 -> "Ace";
            default -> String.valueOf(this.value);
        };
    }

    public String suiteToString() {
        return switch (this.suite) {
            case CLUB -> "Club";
            case HEART -> "Heart";
            case SPADE -> "Spade";
            case DIAMOND -> "Diamond";
        };
    }

    @Override
    public int compareTo(Card otherCard) {
        return Integer.compare(this.value, otherCard.value);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Card cardToCheck)) {
            return false;
        }

        return this.value == cardToCheck.value && this.suite == cardToCheck.suite;
    }

    public enum Suite {
        CLUB("C"),
        DIAMOND("D"),
        HEART("H"),
        SPADE("S");

        public final String label;

        Suite(String label) {
            this.label = label;
        }
    }
}
