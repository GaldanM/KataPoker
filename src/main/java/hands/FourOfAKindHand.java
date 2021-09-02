package hands;

import card.Card;

import java.util.ArrayList;

public class FourOfAKindHand extends Hand {
  int quadrupleValue;

  public FourOfAKindHand() {
    this.handType = HandType.FOUR_OF_A_KIND;
    this.name = "four of a kind";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    for (int i = 0; i < cardList.size(); i += 1) {
      Card cardLeft = cardList.get(i);

      for (int j = i + 1, countKind = 1; j < cardList.size(); j += 1) {
        Card cardRight = cardList.get(j);

        if (cardLeft.value == cardRight.value) {
          countKind += 1;

          if (countKind == 4) {
            this.quadrupleValue = cardLeft.value;
            return true;
          }
        }
      }
    }

    return false;
  }

  @Override
  public CompareResults compare(Hand otherHand) {
    if (otherHand.handType == HandType.STRAIGHT_FLUSH) {
      return new CompareResults(Result.LOSE);
    }

    if (otherHand.handType != this.handType) {
      return new CompareResults(Result.WIN);
    }

    int otherHandQuadrupleValue = ((FourOfAKindHand) otherHand).quadrupleValue;

    if (this.quadrupleValue < otherHandQuadrupleValue) {
      return new CompareResults(Result.LOSE);
    }

    return new CompareResults(Result.WIN);
  }
}