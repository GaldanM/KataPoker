package hands;

import card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class TwoPairsHand extends Hand {
  public ArrayList<Card> pairCards = new ArrayList<>();
  public Card remainingCard;

  public TwoPairsHand() {
    this.handType = HandType.TWO_PAIRS;
    this.name = "two pairs";
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
          this.pairCards.add(cardLeft);

          if (this.pairCards.size() == 2) {
            this.pairCards.sort(Collections.reverseOrder());
            this.remainingCard = cardList.get(maxIndexesSum - foundIndexesSum);
            this.winningCondition = this.getWinningCondition(this.pairCards.get(0), this.pairCards.get(1));

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
        return new CompareResults(Result.WIN, this.winningCondition);
      }
    }

    if (otherHand.handType != this.handType) {
      return new CompareResults(Result.LOSE, otherHand.winningCondition);
    }

    TwoPairsHand otherTwoPairsHand = ((TwoPairsHand) otherHand);

    for (int i = 0; i < 2; i += 1) {
      Card currentThisPairCard = this.pairCards.get(i);
      Card otherHandThisPairCard = otherTwoPairsHand.pairCards.get(i);

      if (currentThisPairCard.value > otherHandThisPairCard.value) {
        return new CompareResults(Result.WIN, this.winningCondition);
      } else if (currentThisPairCard.value < otherHandThisPairCard.value) {
        return new CompareResults(Result.LOSE, otherHand.winningCondition);
      }
    }

    if (this.remainingCard.value > otherTwoPairsHand.remainingCard.value) {
      return new CompareResults(Result.WIN, this.getWinningCondition(this.pairCards.get(0), this.pairCards.get(1), this.remainingCard));
    } else if (this.remainingCard.value < otherTwoPairsHand.remainingCard.value) {
      return new CompareResults(Result.LOSE, this.getWinningCondition(otherTwoPairsHand.pairCards.get(0), otherTwoPairsHand.pairCards.get(1), otherTwoPairsHand.remainingCard));
    }

    return new CompareResults(Result.TIE);
  }

  private String getWinningCondition(Card highestPairCard, Card lowestPairCard) {
    return highestPairCard.valueToString() + " and " + lowestPairCard.valueToString();
  }
  private String getWinningCondition(Card highestPairCard, Card lowestPairCard, Card highestCard) {
    return highestPairCard.valueToString() + " and " + lowestPairCard.valueToString() + " with higher card " + highestCard.valueToString();
  }
}