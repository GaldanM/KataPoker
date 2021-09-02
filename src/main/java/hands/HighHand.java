package hands;

import card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HighHand extends Hand {
  public List<Integer> valuesSorted;

  public HighHand() {
    this.handType = HandType.HIGH;
    this.name = "high card";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    this.valuesSorted = cardList.stream().map(card -> card.value)
        .sorted(Collections.reverseOrder())
        .collect(Collectors.toList());

    return true;
  }

  @Override
  public CompareResults compare(Hand otherHand) {
    if (otherHand.handType != this.handType) {
      return new CompareResults(Result.LOSE);
    }

    List<Integer> otherHandValuesSorted = ((HighHand) otherHand).valuesSorted;

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
