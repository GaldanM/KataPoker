import hands.Hand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class WinnerTest {
  @Test
  void aceWhiteWins() throws Exception {
    Hand blackHand = KataPoker.createHand(new String[] { "2H", "3D", "5S", "9C", "KD" });
    Hand whiteHand = KataPoker.createHand(new String[] { "2C", "3H", "4S", "8C", "AH" });

    String winningOutput = KataPoker.whichHandWins(blackHand, whiteHand);

    Assertions.assertThat(winningOutput).isEqualTo("White wins. - with high card: Ace");
  }
}
