package hands;

import card.Card;

import java.util.ArrayList;
import java.util.List;

public class StraightHand extends Hand {
  int highestValue;

  public StraightHand() {
    this.handType = HandType.STRAIGHT;
    this.name = "straight";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    List<Integer> sortedCardsValues = cardList.stream().map(card -> card.value).sorted().toList();

    for (int i = 0; i < sortedCardsValues.size() - 1; i += 1) {
      int currentValue = sortedCardsValues.get(i);
      int nextValue = sortedCardsValues.get(i + 1);

      if (currentValue != nextValue - 1) {
        return false;
      }
    }

    this.highestValue = sortedCardsValues.get(4);

    return true;
  }

  @Override
  public CompareResults compare(Hand otherHand) {
    HandType[] weakerHandTypes = new HandType[] { HandType.HIGH, HandType.PAIR, HandType.TWO_PAIRS, HandType.THREE_OF_A_KIND };

    for (HandType weakerHandType : weakerHandTypes) {
      if (otherHand.handType == weakerHandType) {
        return new CompareResults(Result.WIN);
      }
    }

    if (otherHand.handType != this.handType) {
      return new CompareResults(Result.LOSE);
    }

    int otherHandValue = ((StraightHand) otherHand).highestValue;

    if (this.highestValue > otherHandValue) {
      return new CompareResults(Result.WIN);
    } else if (this.highestValue == otherHandValue) {
      return new CompareResults(Result.TIE);
    }

    return new CompareResults(Result.LOSE);
  }
}