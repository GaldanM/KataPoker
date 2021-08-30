package hands;

import card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class TwoPairsHand extends Hand {
  public ArrayList<Integer> pairValues = new ArrayList<>();
  public int remainingValue;

  public TwoPairsHand() {
    this.handType = HandType.TWO_PAIRS;
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    int maxIndexesSum = IntStream.range(0, cardList.size()).reduce(0, Integer::sum);
    int foundIndexesSum = 0;

    for (int i = 0; i < cardList.size(); i += 1) {
      Card cardLeft = cardList.get(i);

      for (int j = i + 1; j < cardList.size(); j += 1) {
        Card cardRight = cardList.get(j);

        if (cardLeft.value == cardRight.value) {
          foundIndexesSum += i + j;
          this.pairValues.add(cardLeft.value);

          if (this.pairValues.size() == 2) {
            this.pairValues.sort(Collections.reverseOrder());
            this.remainingValue = cardList.get(maxIndexesSum - foundIndexesSum).value;

            return true;
          }
        }
      }
    }

    return false;
  }

  @Override
  public CompareResults compare(Hand otherHand) {
    HandType[] weakerHandTypes = new HandType[] { HandType.HIGH, HandType.PAIR };
    for (HandType weakerHandType : weakerHandTypes) {
      if (otherHand.handType == weakerHandType) {
        return new CompareResults(Result.WIN);
      }
    }

    if (otherHand.handType != this.handType) {
      return new CompareResults(Result.LOSE);
    }

    TwoPairsHand otherTwoPairsHand = ((TwoPairsHand) otherHand);

    for (int i = 0; i < 2; i += 1) {
      Result comparePairResult = comparePair(this.pairValues.get(i), otherTwoPairsHand.pairValues.get(i));

      if (comparePairResult != Result.TIE) {
        return new CompareResults(comparePairResult);
      }
    }

    if (this.remainingValue > otherTwoPairsHand.remainingValue) {
      return new CompareResults(Result.WIN);
    } else if (this.remainingValue < otherTwoPairsHand.remainingValue) {
      return new CompareResults(Result.LOSE);
    }

    return new CompareResults(Result.TIE);
  }

  private Result comparePair(int v1, int v2) {
    if (v1 > v2) {
      return Result.WIN;
    } else if (v1 < v2) {
      return Result.LOSE;
    }
    return Result.TIE;
  }
}