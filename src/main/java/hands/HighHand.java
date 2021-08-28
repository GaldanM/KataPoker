package hands;

import card.Card;

import java.util.ArrayList;

public class HighHand extends Hand {
  public HighHand() {
    this.handType = HandType.HIGH;
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    return true;
  }
}
