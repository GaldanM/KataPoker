import hands.Hand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class HandsTest {
  @Test
  void createHighHand() throws Exception {
    Hand hand = KataPoker.createHand(new String[] { "2H", "3D", "6S", "9C", "KD" });

    Assertions.assertThat(hand.handType).isEqualTo(Hand.HandType.HIGH);
  }

  @Test
  void createPairHand() throws Exception {
    Hand hand = KataPoker.createHand(new String[] { "2H", "3D", "9S", "9C", "KD" });

    Assertions.assertThat(hand.handType).isEqualTo(Hand.HandType.PAIR);
  }

  @Test
  void createTwoPairsHand() throws Exception {
    Hand hand = KataPoker.createHand(new String[] { "3H", "3D", "9S", "9C", "KD" });

    Assertions.assertThat(hand.handType).isEqualTo(Hand.HandType.TWO_PAIRS);
  }

  @Test
  void createThreeOfAKindHand() throws Exception {
    Hand hand = KataPoker.createHand(new String[] { "3H", "3D", "3S", "9C", "KD" });

    Assertions.assertThat(hand.handType).isEqualTo(Hand.HandType.THREE_OF_A_KIND);
  }

  @Test
  void createStraightHand() throws Exception {

    Hand handWithConsecutiveFigures = KataPoker.createHand(new String[] { "3H", "5D", "4S", "6C", "2D" });

    Assertions.assertThat(handWithConsecutiveFigures.handType).isEqualTo(Hand.HandType.STRAIGHT);
  }

  @Test
  void createFlushHand() throws Exception {
    Hand hand = KataPoker.createHand(new String[] { "2D", "KD", "6D", "TD", "9D" });

    Assertions.assertThat(hand.handType).isEqualTo(Hand.HandType.FLUSH);
  }

  @Test
  void createFullHouseHand() throws Exception {
    Hand handLowestIsTriple = KataPoker.createHand(new String[] { "2D", "KD", "2C", "KS", "2S" });
    Hand handHighestIsTriple = KataPoker.createHand(new String[] { "KD", "2D", "KC", "2S", "KS" });

    Assertions.assertThat(handLowestIsTriple.handType).isEqualTo(Hand.HandType.FULL_HOUSE);
    Assertions.assertThat(handHighestIsTriple.handType).isEqualTo(Hand.HandType.FULL_HOUSE);
  }

  @Test
  void createFourOfAKindHand() throws Exception {
    Hand hand = KataPoker.createHand(new String[] { "2D", "2H", "2C", "KS", "2S" });

    Assertions.assertThat(hand.handType).isEqualTo(Hand.HandType.FOUR_OF_A_KIND);
  }

  @Test
  void createStraightFlushHand() throws Exception {
    Hand hand = KataPoker.createHand(new String[] { "3D", "6D", "5D", "2D", "4D" });

    Assertions.assertThat(hand.handType).isEqualTo(Hand.HandType.STRAIGHT_FLUSH);
  }

  @Test
  void createHandWithSameCard() {
    Throwable thrown = Assertions.catchThrowable(() -> KataPoker.createHand(new String[] { "3D", "3D", "5D", "2D", "4D" }));

    Assertions.assertThat(thrown).isInstanceOf(Exception.class);
  }
}
