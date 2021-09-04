package hands;

import card.Card;

import java.util.ArrayList;

public class ThreeOfAKindHand extends Hand {
  Card.Figure threeOfAKindFigure;

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

        if (cardLeft.figure == cardRight.figure) {
          countKind += 1;

          if (countKind == 3) {
            this.threeOfAKindFigure = cardLeft.figure;
            this.winningCondition = cardLeft.figure.label;

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
        return new CompareResults(Result.WIN, this.winningCondition);
      }
    }

    if (otherHand.handType != this.handType) {
      return new CompareResults(Result.LOSE, otherHand.winningCondition);
    }

    Card.Figure otherHandThreeOfAKindFigure = ((ThreeOfAKindHand) otherHand).threeOfAKindFigure;
    if (this.threeOfAKindFigure.value > otherHandThreeOfAKindFigure.value) {
      return new CompareResults(Result.WIN, this.winningCondition);
    }

    return new CompareResults(Result.LOSE, otherHand.winningCondition);
  }
}