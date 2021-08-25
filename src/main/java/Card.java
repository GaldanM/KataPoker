public class Card {
    int value;
    Suite suite;

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
