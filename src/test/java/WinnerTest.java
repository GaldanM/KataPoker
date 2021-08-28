import card.Card;
import hands.Hand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class WinnerTest {
  @Test
  void aceWhiteWins() throws Exception {
    ArrayList<Card> cardList = new ArrayList<>(
        List.of(
            new Card("2H"),
            new Card("3D"),
            new Card("5S"),
            new Card("9C"),
            new Card("KD")
        )
    );
    Hand blackHand = KataPoker.createHand(cardList);

    cardList = new ArrayList<>(
        List.of(
            new Card("2C"),
            new Card("3H"),
            new Card("4S"),
            new Card("8C"),
            new Card("AH")
        )
    );
    Hand whiteHand = KataPoker.createHand(cardList);

    String winningOutput = KataPoker.whichHandWins(blackHand, whiteHand);

    Assertions.assertThat(winningOutput).isEqualTo("White wins. - with high card: Ace");
  }
}
