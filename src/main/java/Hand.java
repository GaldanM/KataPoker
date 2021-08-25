import java.util.ArrayList;

abstract class Hand {
  HandType handType;

  abstract boolean check(ArrayList<Card> cardList);

  enum HandType {
    HIGH,
    PAIR,
    TWO_PAIRS,
  }
}
