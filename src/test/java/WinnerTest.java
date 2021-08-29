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
