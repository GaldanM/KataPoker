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
      int currentValue = this.sortedCards.get(i).value;
      int nextValue = this.sortedCards.get(i + 1).value;

      if (currentValue != nextValue - 1) {
        return false;
      }
    }

    this.highestValue = this.sortedCards.get(4).value;
    this.winningCondition = this.sortedCards.stream().map(Card::valueToString).collect(Collectors.joining(", "));

    return true;
  }

  @Override
  public CompareResults compare(Hand otherHand) {
    HandType[] weakerHandTypes = new HandType[] { HandType.HIGH, HandType.PAIR, HandType.TWO_PAIRS, HandType.THREE_OF_A_KIND };

    for (HandType weakerHandType : weakerHandTypes) {
      if (otherHand.handType == weakerHandType) {
        return new CompareResults(Result.WIN, this.winningCondition);
      }
    }

    if (otherHand.handType != this.handType) {
      return new CompareResults(Result.LOSE, otherHand.winningCondition);
    }

    int otherHandValue = ((StraightHand) otherHand).highestValue;

    if (this.highestValue > otherHandValue) {
      return new CompareResults(Result.WIN, this.winningCondition);
    } else if (this.highestValue == otherHandValue) {
      return new CompareResults(Result.TIE);
    }

    return new CompareResults(Result.LOSE, otherHand.winningCondition);
  }
}