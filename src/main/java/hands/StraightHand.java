package hands;

import card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StraightHand extends Hand {
  List<Card> sortedCards;
  int highestValue;

  public StraightHand() {
    this.handType = HandType.STRAIGHT;
    this.name = "straight";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    this.sortedCards = cardList.stream().sorted().toList();

    for (int i = 0; i < this.sortedCards.size() - 1; i += 1) {
      int currentValue = this.sortedCards.get(i).figure.value;
      int nextValue = this.sortedCards.get(i + 1).figure.value;

      if (currentValue != nextValue - 1) {
        return false;
      }
    }

    this.highestValue = this.sortedCards.get(4).figure.value;
    this.winningCondition = this.sortedCards.stream().map(card -> card.figure.label).collect(Collectors.joining(", "));

    return true;
  }

  @Override
  public HandCompare compare(Hand otherHand) {
    HandType[] weakerHandTypes = new HandType[] { HandType.HIGH, HandType.PAIR, HandType.TWO_PAIRS, HandType.THREE_OF_A_KIND };

    for (HandType weakerHandType : weakerHandTypes) {
      if (otherHand.handType == weakerHandType) {
        return new HandCompare(HandCompare.Result.WIN, this.winningCondition);
      }
    }

    if (otherHand.handType != this.handType) {
      return new HandCompare(HandCompare.Result.LOSE, otherHand.winningCondition);
    }

    int otherStraightHandHighestValue = ((StraightHand) otherHand).highestValue;

    if (this.highestValue > otherStraightHandHighestValue) {
      return new HandCompare(HandCompare.Result.WIN, this.winningCondition);
    } else if (this.highestValue == otherStraightHandHighestValue) {
      return new HandCompare(HandCompare.Result.TIE);
    }

    return new HandCompare(HandCompare.Result.LOSE, otherHand.winningCondition);
  }
}