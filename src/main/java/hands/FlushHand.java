package hands;

import card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FlushHand extends Hand {
  List<Integer> valuesSorted;

  public FlushHand() {
    this.handType = HandType.FLUSH;
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

    this.valuesSorted = cardList.stream().map(card -> card.value).sorted(Collections.reverseOrder()).collect(Collectors.toList());

    return true;
  }

  @Override
  public CompareResults compare(Hand otherHand) {
    HandType[] strongerHandTypes = new HandType[] { HandType.FULL_HOUSE, HandType.FOUR_OF_A_KIND, HandType.STRAIGHT_FLUSH };

    for (HandType strongerHandType : strongerHandTypes) {
      if (otherHand.handType == strongerHandType) {
        return new CompareResults(Result.LOSE);
      }
    }

    if (otherHand.handType != HandType.FLUSH) {
      return new CompareResults(Result.WIN);
    }

    List<Integer> otherHandValuesSorted = ((FlushHand) otherHand).valuesSorted;

    for (int i = 0; i < this.valuesSorted.size(); i += 1) {
      int compareResults = this.valuesSorted.get(i).compareTo(otherHandValuesSorted.get(i));

      if (compareResults > 0) {
        return new CompareResults(Result.WIN);
      } else if (compareResults < 0) {
        return new CompareResults(Result.LOSE);
      }
    }

    return new CompareResults(Result.TIE);
  }
}