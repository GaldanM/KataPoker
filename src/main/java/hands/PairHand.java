package hands;

import card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PairHand extends Hand {
  public int highestPairValue;
  public List<Integer> restValuesOrdered;

  public PairHand() {
    this.handType = HandType.PAIR;
    this.name = "pair";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    for (int i = 0; i < cardList.size(); i += 1) {
      Card cardLeft = cardList.get(i);

      for (int j = i + 1; j < cardList.size(); j += 1) {
        Card cardRight = cardList.get(j);

        if (cardLeft.value == cardRight.value) {
          ArrayList<Card> tmp = new ArrayList<>(cardList);
          tmp.remove(i);
          tmp.remove(j - 1);
          this.restValuesOrdered = List.copyOf(tmp.stream().map(card -> card.value).sorted(Collections.reverseOrder()).toList());
          this.highestPairValue = cardLeft.value;

          return true;
        }
      }
    }
    return false;
  }

  @Override
  public CompareResults compare(Hand otherHand) {
    if (otherHand.handType == HandType.HIGH) {
      return new CompareResults(Result.WIN);
    }

    if (otherHand.handType == this.handType) {
      int highestPairCompareResult = Integer.compare(this.highestPairValue, ((PairHand) otherHand).highestPairValue);

      if (highestPairCompareResult > 0) {
        return new CompareResults(Result.WIN);
      } else if (highestPairCompareResult == 0) {
        List<Integer> otherHandValuesSorted = ((PairHand) otherHand).restValuesOrdered;

        for (int i = 0; i < this.restValuesOrdered.size(); i += 1) {
          int compareResults = this.restValuesOrdered.get(i).compareTo(otherHandValuesSorted.get(i));

          if (compareResults > 0) {
            return new CompareResults(Result.WIN);
          } else if (compareResults < 0) {
            return new CompareResults(Result.LOSE);
          }
        }

        return new CompareResults(Result.TIE);
      }
    }

    return new CompareResults(Result.LOSE);
  }
}