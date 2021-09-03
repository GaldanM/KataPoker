import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {
  @Test
  void firstSuggestedTest() throws Exception {
    String input = "Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH";

    Main main = new Main();
    String output = main.execute(input);

    Assertions.assertThat(output).isEqualTo("White wins. - with high card: Ace");
  }

  @Test
  void secondSuggestedTest() throws Exception {
    String input = "Black: 2H 4S 4C 2D 4H White: 2S 8S AS QS 3S";

    Main main = new Main();
    String output = main.execute(input);

    Assertions.assertThat(output).isEqualTo("Black wins. - with full house: 4 over 2");
  }

  @Test
  void thirdSuggestedTest() throws Exception {
    String input = "Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C KH";

    Main main = new Main();
    String output = main.execute(input);

    Assertions.assertThat(output).isEqualTo("Black wins. - with high card: 9");
  }

  @Test
  void lastSuggestedTest() throws Exception {
    String input = "Black: 2H 3D 5S 9C KD White: 2D 3H 5C 9S KH";

    Main main = new Main();
    String output = main.execute(input);

    Assertions.assertThat(output).isEqualTo("Tie.");
  }
}
