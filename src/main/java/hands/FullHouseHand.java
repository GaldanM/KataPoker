package hands;

import card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FullHouseHand extends Hand {
  int tripleValue;

  public FullHouseHand() {
    this.handType = HandType.FULL_HOUSE;
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
        this.tripleValue = cardListSorted.get(0).value;
      } else if (isLastTwoTheTriple) {
        this.tripleValue = cardListSorted.get(4).value;
      }

      return isFirstTwoTheTriple || isLastTwoTheTriple;
    }

    return false;
  }

  @Override
  public CompareResults compare(Hand otherHand) {
    HandType[] strongerHandTypes = new HandType[] { HandType.FOUR_OF_A_KIND, HandType.STRAIGHT_FLUSH };

    for (HandType strongerHandType : strongerHandTypes) {
      if (otherHand.handType == strongerHandType) {
        return new CompareResults(Result.LOSE);
      }
    }

    if (otherHand.handType != this.handType) {
      return new CompareResults(Result.WIN);
    }

    int otherHandTripleValue = ((FullHouseHand) otherHand).tripleValue;

    if (this.tripleValue < otherHandTripleValue) {
      return new CompareResults(Result.LOSE);
    }

    return new CompareResults(Result.WIN);
  }
}
