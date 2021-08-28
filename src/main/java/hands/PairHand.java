package hands;

import card.Card;

import java.util.ArrayList;

public class PairHand extends Hand {
  public PairHand() {
    this.handType = HandType.PAIR;
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    for (int i = 0; i < cardList.size(); i += 1) {
      Card cardLeft = cardList.get(i);

      for (int j = i + 1; j < cardList.size(); j += 1) {
        Card cardRight = cardList.get(j);

        if (cardLeft.value == cardRight.value) {
          return true;
        }
      }
    }
    return false;
  }
}