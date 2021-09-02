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
    this.winningCondition = getWinningCondition(this.cardsSorted.get(0));

    return true;
  }

  @Override
  public CompareResults compare(Hand otherHand) {
    HandType[] strongerHandTypes = new HandType[] { HandType.FULL_HOUSE, HandType.FOUR_OF_A_KIND, HandType.STRAIGHT_FLUSH };

    for (HandType strongerHandType : strongerHandTypes) {
      if (otherHand.handType == strongerHandType) {
        return new CompareResults(Result.LOSE, otherHand.winningCondition);
      }
    }

    if (otherHand.handType != HandType.FLUSH) {
      return new CompareResults(Result.WIN, this.winningCondition);
    }

    List<Card> otherHandCardsSorted = ((FlushHand) otherHand).cardsSorted;

    for (int i = 0; i < this.cardsSorted.size(); i += 1) {
      Card currentThisCard = this.cardsSorted.get(i);
      Card currentOtherHandCard = otherHandCardsSorted.get(i);

      if (currentThisCard.value > currentOtherHandCard.value) {
        return new CompareResults(Result.WIN, this.getWinningConditionHigher(currentThisCard));
      } else if (currentThisCard.value < currentOtherHandCard.value) {
        return new CompareResults(Result.LOSE, this.getWinningConditionHigher(currentOtherHandCard));
      }
    }

    return new CompareResults(Result.TIE);
  }

  private String getWinningCondition(Card cardFlush) {
    return cardFlush.suiteToString();
  }
  private String getWinningConditionHigher(Card higherCard) {
    return higherCard.suiteToString() + " and higher card " + higherCard.valueToString();
  }
}