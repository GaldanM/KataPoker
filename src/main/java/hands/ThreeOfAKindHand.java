package hands;

import card.Card;

import java.util.ArrayList;

public class ThreeOfAKindHand extends Hand {
  int threeOfAKindValue;

  public ThreeOfAKindHand() {
    this.handType = HandType.THREE_OF_A_KIND;
    this.name = "three of a kind";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    for (int i = 0; i < cardList.size(); i += 1) {
      Card cardLeft = cardList.get(i);

      for (int j = i + 1, countKind = 1; j < cardList.size(); j += 1) {
        Card cardRight = cardList.get(j);

        if (cardLeft.value == cardRight.value) {
          countKind += 1;

          if (countKind == 3) {
            this.threeOfAKindValue = cardLeft.value;
            return true;
          }
        }
      }
    }

    return false;
  }

  @Override
  public CompareResults compare(Hand otherHand) {
    HandType[] weakerHandTypes = new HandType[] { HandType.HIGH, HandType.PAIR, HandType.TWO_PAIRS };

    for (HandType weakerHandType : weakerHandTypes) {
      if (otherHand.handType == weakerHandType) {
        return new CompareResults(Result.WIN);
      }
    }

    if (otherHand.handType != this.handType) {
      return new CompareResults(Result.LOSE);
    }

    int otherHandValue = ((ThreeOfAKindHand) otherHand).threeOfAKindValue;

    if (this.threeOfAKindValue > otherHandValue) {
      return new CompareResults(Result.WIN);
    }

    return new CompareResults(Result.LOSE);
  }
}