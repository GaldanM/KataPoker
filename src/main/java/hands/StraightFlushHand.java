package hands;

import card.Card;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class StraightFlushHand extends Hand {
  int highestValue;

  public StraightFlushHand() {
    this.handType = HandType.STRAIGHT_FLUSH;
    this.name = "straight flush";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    StraightHand straightHand = new StraightHand();

    boolean isStraight = straightHand.check(cardList);
    boolean isFlush = new FlushHand().check(cardList);

    this.highestValue = straightHand.highestValue;
    this.winningCondition = straightHand.sortedCards.stream()
        .map(card -> card.figure.label)
        .collect(Collectors.joining(", ")) +
        " of " +
        straightHand.sortedCards.get(0).suite.label;

    return isStraight && isFlush;
  }

  @Override
  public CompareResults compare(Hand otherHand) {
    if (otherHand.handType != this.handType) {
      return new CompareResults(Result.WIN, this.winningCondition);
    }

    int otherHandHighestValueOfStraight = ((StraightFlushHand) otherHand).highestValue;

    if (this.highestValue < otherHandHighestValueOfStraight) {
      return new CompareResults(Result.LOSE, otherHand.winningCondition);
    } else if (this.highestValue == otherHandHighestValueOfStraight) {
      return new CompareResults(Result.TIE);
    }

    return new CompareResults(Result.WIN, this.winningCondition);
  }
}