import hands.Hand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class WinnerTest {
  @ParameterizedTest(name = "{0}")
  @MethodSource("highCardTestsGenerator")
  void highCardTest(String testName, WinnerTestParameter parameters) {
    String winningOutput = KataPoker.whichHandWins(parameters.blackHand, parameters.whiteHand);

    Assertions.assertThat(winningOutput).isEqualTo(parameters.expectedOutput);
  }
  public static Stream<Arguments> highCardTestsGenerator() throws Exception {
    WinnerTestParameter[] parameters = new WinnerTestParameter[] {
        new WinnerTestParameter(
            new String[] { "2C", "3H", "4S", "5C", "AH" },
            new String[] { "2H", "3S", "4D", "4H", "AD" },
            "White wins."
        ),
        new WinnerTestParameter(
            new String[] { "2H", "3D", "5S", "9C", "KD" },
            new String[] { "2C", "3H", "4S", "8C", "AH" },
            "White wins."
        ),
        new WinnerTestParameter(
            new String[] { "2C", "3H", "4S", "8C", "AH" },
            new String[] { "2H", "3D", "5S", "9C", "KD" },
            "Black wins."
        ),
        new WinnerTestParameter(
            new String[] { "2C", "3H", "4S", "8C", "AH" },
            new String[] { "2H", "3S", "4D", "8H", "AD" },
            "Tie."
        ),
        new WinnerTestParameter(
            new String[] { "2C", "3H", "4S", "7C", "AH" },
            new String[] { "2H", "3S", "4D", "8H", "AD" },
            "White wins."
        ),
        new WinnerTestParameter(
            new String[] { "2H", "3S", "4D", "8H", "AD" },
            new String[] { "2C", "3H", "4S", "7C", "AH" },
            "Black wins."
        ),
    };

    return Stream.of(
        Arguments.of("High Card against STRONGER Hand", parameters[0]),
        Arguments.of("High Card against STRONGER High Card", parameters[1]),
        Arguments.of("High Card against WEAKER High Card", parameters[2]),
        Arguments.of("High Card against SAME High Card", parameters[3]),
        Arguments.of("High Card against SAME High Card AND STRONGER second High Card", parameters[4]),
        Arguments.of("High Card against SAME High Card AND WEAKER second High Card", parameters[5])
    );
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("pairTestsGenerator")
  void pairTest(String testName, WinnerTestParameter parameters) {
    String winningOutput = KataPoker.whichHandWins(parameters.blackHand, parameters.whiteHand);

    Assertions.assertThat(winningOutput).isEqualTo(parameters.expectedOutput);
  }
  public static Stream<Arguments> pairTestsGenerator() throws Exception {
    WinnerTestParameter[] parameters = new WinnerTestParameter[] {
        new WinnerTestParameter(
            new String[] { "2C", "3H", "4S", "4C", "AH" },
            new String[] { "2H", "3S", "4D", "8H", "AD" },
            "Black wins."
        ),
        new WinnerTestParameter(
            new String[] { "2C", "3H", "4S", "4C", "AH" },
            new String[] { "2H", "2S", "3D", "3S", "AD" },
            "White wins."
        ),
        new WinnerTestParameter(
            new String[] { "2C", "3H", "4S", "4C", "AH" },
            new String[] { "2H", "2S", "3D", "4S", "AD" },
            "Black wins."
        ),
        new WinnerTestParameter(
            new String[] { "2C", "3H", "4S", "4C", "AH" },
            new String[] { "8H", "8S", "3D", "4S", "AD" },
            "White wins."
        ),
        new WinnerTestParameter(
            new String[] { "2C", "3H", "4S", "4C", "AH" },
            new String[] { "4H", "4D", "3D", "2S", "KD" },
            "Black wins."
        ),
        new WinnerTestParameter(
            new String[] { "4H", "4D", "3D", "2S", "KD" },
            new String[] { "2C", "3H", "4S", "4C", "AH" },
            "White wins."
        ),
        new WinnerTestParameter(
            new String[] { "2C", "3H", "4S", "4C", "AH" },
            new String[] { "4H", "4D", "3D", "2S", "AD" },
            "Tie."
        ),
    };

    return Stream.of(
        Arguments.of("Pair against WEAKER Hand", parameters[0]),
        Arguments.of("Pair against STRONGER Hand", parameters[1]),
        Arguments.of("Pair against WEAKER Pair", parameters[2]),
        Arguments.of("Pair against STRONGER Pair", parameters[3]),
        Arguments.of("Pair against SAME Pair AND WEAKER highest card among the rest", parameters[4]),
        Arguments.of("Pair against SAME Pair AND STRONGER highest card among the rest", parameters[5]),
        Arguments.of("Pair against SAME Pair AND SAME cards among the rest", parameters[6])
    );
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("twoPairsTestsGenerator")
  void twoPairsTest(String testName, WinnerTestParameter parameters) {
    String winningOutput = KataPoker.whichHandWins(parameters.blackHand, parameters.whiteHand);

    Assertions.assertThat(winningOutput).isEqualTo(parameters.expectedOutput);
  }
  public static Stream<Arguments> twoPairsTestsGenerator() throws Exception {
    WinnerTestParameter[] parameters = new WinnerTestParameter[] {
        new WinnerTestParameter(
            new String[] { "2C", "2H", "4S", "4C", "AH" },
            new String[] { "3H", "3S", "4D", "8H", "AD" },
            "Black wins."
        ),
        new WinnerTestParameter(
            new String[] { "2C", "2H", "4S", "4C", "AH" },
            new String[] { "3H", "3S", "3D", "8H", "AD" },
            "White wins."
        ),
        new WinnerTestParameter(
            new String[] { "9C", "9H", "8S", "8C", "AH" },
            new String[] { "3D", "3S", "2D", "2H", "AD" },
            "Black wins."
        ),
        new WinnerTestParameter(
            new String[] { "9C", "9H", "8S", "8C", "AH" },
            new String[] { "9D", "9S", "3D", "3H", "AD" },
            "Black wins."
        ),
        new WinnerTestParameter(
            new String[] { "9C", "9H", "8S", "8C", "AH" },
            new String[] { "9D", "9S", "8D", "8H", "KD" },
            "Black wins."
        ),
        new WinnerTestParameter(
            new String[] { "9C", "9H", "8S", "8C", "KH" },
            new String[] { "9D", "9S", "8D", "8H", "AD" },
            "White wins."
        ),
        new WinnerTestParameter(
            new String[] { "9C", "9H", "8S", "8C", "AH" },
            new String[] { "9D", "9S", "8D", "8H", "AD" },
            "Tie."
        ),
        new WinnerTestParameter(
            new String[] { "9C", "9H", "7S", "7C", "AH" },
            new String[] { "9D", "9S", "8D", "8H", "AD" },
            "White wins."
        ),
        new WinnerTestParameter(
            new String[] { "9C", "9H", "7S", "7C", "AH" },
            new String[] { "TD", "TS", "8D", "8H", "AD" },
            "White wins."
        ),
    };

    return Stream.of(
        Arguments.of("Two Pairs against WEAKER Hand", parameters[0]),
        Arguments.of("Two Pairs against STRONGER Hand", parameters[1]),
        Arguments.of("Two Pairs against Two Pairs with WEAKER highest pair", parameters[2]),
        Arguments.of("Two Pairs against Two Pairs with SAME highest pair AND WEAKER second pair", parameters[3]),
        Arguments.of("Two Pairs against Two Pairs with SAME highest pair AND SAME second pair AND WEAKER remaining card", parameters[4]),
        Arguments.of("Two Pairs against Two Pairs with SAME highest pair AND SAME second pair AND STRONGER remaining card", parameters[5]),
        Arguments.of("Two Pairs against Two Pairs with SAME highest pair AND SAME second pair AND SAME remaining card", parameters[6]),
        Arguments.of("Two Pairs against Two Pairs with SAME highest pair AND STRONGER second pair", parameters[7]),
        Arguments.of("Two Pairs against Two Pairs with STRONGER highest pair", parameters[8])
    );
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("threeOfAKindTestsGenerator")
  void threeOfAKindTest(String testName, WinnerTestParameter parameters) {
    String winningOutput = KataPoker.whichHandWins(parameters.blackHand, parameters.whiteHand);

    Assertions.assertThat(winningOutput).isEqualTo(parameters.expectedOutput);
  }
  public static Stream<Arguments> threeOfAKindTestsGenerator() throws Exception {
    WinnerTestParameter[] parameters = new WinnerTestParameter[] {
        new WinnerTestParameter(
            new String[] { "2C", "2H", "2S", "4C", "AH" },
            new String[] { "3H", "5S", "4D", "8H", "AD" },
            "Black wins."
        ),
        new WinnerTestParameter(
            new String[] { "2C", "2H", "2S", "4C", "AH" },
            new String[] { "2D", "3S", "4D", "5H", "6D" },
            "White wins."
        ),
        new WinnerTestParameter(
            new String[] { "3C", "3H", "3S", "4C", "AH" },
            new String[] { "2D", "2S", "2H", "5H", "6D" },
            "Black wins."
        ),
        new WinnerTestParameter(
            new String[] { "2C", "2H", "2S", "4C", "AH" },
            new String[] { "3D", "3S", "3H", "5H", "6D" },
            "White wins."
        ),
    };

    return Stream.of(
        Arguments.of("Three of a Kind against WEAKER Hand", parameters[0]),
        Arguments.of("Three of a Kind against STRONGER Hand", parameters[1]),
        Arguments.of("Three of a Kind against WEAKER Three of a Kind", parameters[2]),
        Arguments.of("Three of a Kind against STRONGER Three of a Kind", parameters[3])
    );
  }

  public static class WinnerTestParameter {
    Hand blackHand;
    Hand whiteHand;
    String expectedOutput;

    public WinnerTestParameter(
        String[] blackHandCardDescriptions,
        String[] whiteHandCardDescriptions,
        String expectedOutput) throws Exception {
      this.blackHand = KataPoker.createHand(blackHandCardDescriptions);
      this.whiteHand = KataPoker.createHand(whiteHandCardDescriptions);
      this.expectedOutput = expectedOutput;
    }
  }
}
