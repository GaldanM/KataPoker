import java.util.ArrayList;

abstract class Hand {
  HandType handType;

  abstract boolean check(ArrayList<Card> cardList);

  enum HandType {
    HIGH,
    PAIR,
    TWO_PAIRS,
    THREE_OF_A_KIND,
    STRAIGHT,
    FLUSH,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
  }
}
