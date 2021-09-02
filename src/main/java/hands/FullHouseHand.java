package hands;

import card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FullHouseHand extends Hand {
  Card tripleCard;
  Card pairCard;

  public FullHouseHand() {
    this.handType = HandType.FULL_HOUSE;
    this.name = "full house";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    List<Card> cardListSorted = cardList.stream().sorted().collect(Collectors.toList());

    boolean firstTwoAreTheSame = cardListSorted.get(0).value == cardListSorted.get(1).value;
    boolean lastTwoAreTheSame = cardListSorted.get(3).value == cardListSorted.get(4).value;

    if (firstTwoAreTheSame && lastTwoAreTheSame) {
      boolean isFirstTwoTheTriple = cardListSorted.get(2).value == cardListSorted.get(0).value;
      boolean isLastTwoTheTriple = cardListSorted.get(2).value == cardListSorted.get(4).value;

      if (isFirstTwoTheTriple) {
        this.tripleCard = cardListSorted.get(0);
        this.pairCard = cardListSorted.get(4);
      } else if (isLastTwoTheTriple) {
        this.tripleCard = cardListSorted.get(4);
        this.pairCard = cardListSorted.get(0);
      }
      this.winningCondition = this.tripleCard.valueToString() + " over " + this.pairCard.valueToString();

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

    int otherHandTripleValue = ((FullHouseHand) otherHand).tripleCard.value;

    if (this.tripleCard.value < otherHandTripleValue) {
      return new CompareResults(Result.LOSE, otherHand.winningCondition);
    }

    return new CompareResults(Result.WIN, this.winningCondition);
  }
}
