package hands;

import card.Card;

import java.util.ArrayList;

public abstract class Hand {
  public HandType handType;

  public abstract boolean check(ArrayList<Card> cardList);

  public enum HandType {
    HIGH,
    PAIR,
    TWO_PAIRS,
    THREE_OF_A_KIND,
    STRAIGHT,
    FLUSH,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    STRAIGHT_FLUSH,
  }
}