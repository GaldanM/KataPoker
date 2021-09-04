package hands;

import card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FlushHand extends Hand {
  List<Card> cardsSorted;

  public FlushHand() {
    this.handType = HandType.FLUSH;
    this.name = "flush";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    Card.Suite suiteToCheck = cardList.get(0).suite;

    for (int i = 1; i < cardList.size(); i += 1) {
      Card current = cardList.get(i);

      if (current.suite != suiteToCheck) {
        return false;
      }
    }

    this.cardsSorted = cardList.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
    this.winningCondition = getWinningCondition(this.cardsSorted.get(0).suite);

    return true;
  }

  @Override
  public HandCompare compare(Hand otherHand) {
    HandType[] strongerHandTypes = new HandType[] { HandType.FULL_HOUSE, HandType.FOUR_OF_A_KIND, HandType.STRAIGHT_FLUSH };

    for (HandType strongerHandType : strongerHandTypes) {
      if (otherHand.handType == strongerHandType) {
        return new HandCompare(HandCompare.Result.LOSE, otherHand.winningCondition);
      }
    }

    if (otherHand.handType != this.handType) {
      return new HandCompare(HandCompare.Result.WIN, this.winningCondition);
    }

    List<Card> otherHandCardsSorted = ((FlushHand) otherHand).cardsSorted;

    for (int i = 0; i < this.cardsSorted.size(); i += 1) {
      Card currentThisCard = this.cardsSorted.get(i);
      Card currentOtherHandCard = otherHandCardsSorted.get(i);

      if (currentThisCard.figure.value > currentOtherHandCard.figure.value) {
        return new HandCompare(HandCompare.Result.WIN, this.getWinningCondition(currentThisCard));
      } else if (currentThisCard.figure.value < currentOtherHandCard.figure.value) {
        return new HandCompare(HandCompare.Result.LOSE, this.getWinningCondition(currentOtherHandCard));
      }
    }

    return new HandCompare(HandCompare.Result.TIE);
  }

  private String getWinningCondition(Card.Suite suiteFlush) {
    return suiteFlush.label;
  }
  private String getWinningCondition(Card flushHighestCard) {
    return flushHighestCard.suite.label + " and higher card " + flushHighestCard.figure.label;
  }
}