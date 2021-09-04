package hands;

import card.Card;

import java.util.ArrayList;

public abstract class Hand {
  public HandType handType;
  public String name;
  public String winningCondition;

  public abstract boolean check(ArrayList<Card> cardList);

  public abstract HandCompare compare(Hand whiteHand);

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

  public static class HandCompare {
    public Result result;
    public String winningCondition;

    public HandCompare(Result result, String winningCondition) {
      this.result = result;
      this.winningCondition = winningCondition;
    }

    public HandCompare(Result result) {
      this.result = result;
    }

    public enum Result {
      WIN,
      LOSE,
      TIE
    }
  }
}
