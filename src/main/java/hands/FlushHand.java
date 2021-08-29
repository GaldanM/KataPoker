package hands;

import card.Card;

import java.util.ArrayList;

public class FlushHand extends Hand {
  public FlushHand() {
    this.handType = HandType.FLUSH;
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    Card.Suite suiteToCheck = cardList.get(0).suite;

    for (int i = 1; i < cardList.size(); i += 1) {
      Card current = cardList.get(i);

      if (current.suite != suiteToCheck) {
        return false;
      }
    }

    return true;
  }

  @Override
  public CompareResults compare(Hand whiteHand) {
    return null;
  }
}