package hands;

import card.Card;

import java.util.ArrayList;

public class StraightFlushHand extends Hand {
  public StraightFlushHand() {
    this.handType = HandType.STRAIGHT_FLUSH;
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    boolean isStraight = new StraightHand().check(cardList);
    boolean isFlush = new FlushHand().check(cardList);

    return isStraight && isFlush;
  }

  @Override
  public CompareResults compare(Hand whiteHand) {
    return null;
  }
}