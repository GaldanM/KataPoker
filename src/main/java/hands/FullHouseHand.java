package hands;

import card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FullHouseHand extends Hand {
  Card.Figure tripleFigure;
  Card.Figure pairFigure;

  public FullHouseHand() {
    this.handType = HandType.FULL_HOUSE;
    this.label = "full house";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    List<Card> cardListSorted = cardList.stream().sorted().collect(Collectors.toList());

    boolean firstTwoAreTheSame = cardListSorted.get(0).figure == cardListSorted.get(1).figure;
    boolean lastTwoAreTheSame = cardListSorted.get(3).figure == cardListSorted.get(4).figure;

    if (firstTwoAreTheSame && lastTwoAreTheSame) {
      boolean isFirstTwoTheTriple = cardListSorted.get(2).figure == cardListSorted.get(0).figure;
      boolean isLastTwoTheTriple = cardListSorted.get(2).figure == cardListSorted.get(4).figure;

      if (isFirstTwoTheTriple) {
        this.tripleFigure = cardListSorted.get(0).figure;
        this.pairFigure = cardListSorted.get(4).figure;
      } else if (isLastTwoTheTriple) {
        this.tripleFigure = cardListSorted.get(4).figure;
        this.pairFigure = cardListSorted.get(0).figure;
      }
      this.winningCondition = this.tripleFigure.label + " over " + this.pairFigure.label;

      return isFirstTwoTheTriple || isLastTwoTheTriple;
    }

    return false;
  }

  @Override
  public CompareResults compare(Hand otherHand) {
    HandType[] strongerHandTypes = new HandType[] { HandType.FOUR_OF_A_KIND, HandType.STRAIGHT_FLUSH };

    for (HandType strongerHandType : strongerHandTypes) {
      if (otherHand.handType == strongerHandType) {
        return new CompareResults(Result.LOSE, otherHand.winningCondition);
      }
    }

    if (otherHand.handType != this.handType) {
      return new CompareResults(Result.WIN, this.winningCondition);
    }

    int otherHandTripleValue = ((FullHouseHand) otherHand).tripleFigure.value;

    if (this.tripleFigure.value < otherHandTripleValue) {
      return new CompareResults(Result.LOSE, otherHand.winningCondition);
    }

    return new CompareResults(Result.WIN, this.winningCondition);
  }
}
