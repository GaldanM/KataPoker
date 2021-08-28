package hands;

import card.Card;

import java.util.ArrayList;

public class TwoPairsHand extends Hand {
  public TwoPairsHand() {
    this.handType = HandType.TWO_PAIRS;
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    int countPairs = 0;

    for (int i = 0; i < cardList.size(); i += 1) {
      Card cardLeft = cardList.get(i);

      for (int j = i + 1; j < cardList.size(); j += 1) {
        Card cardRight = cardList.get(j);

        if (cardLeft.value == cardRight.value) {
          countPairs += 1;

          if (countPairs == 2) {
            return true;
          }
        }
      }
    }

    return false;
  }
}