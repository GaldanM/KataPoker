package hands;

import card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class TwoPairsHand extends Hand {
  public ArrayList<Card.Figure> pairFigures = new ArrayList<>();
  public Card.Figure remainingFigure;

  public TwoPairsHand() {
    this.handType = HandType.TWO_PAIRS;
    this.label = "two pairs";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    int maxIndexesSum = IntStream.range(0, cardList.size()).reduce(0, Integer::sum);
    int foundIndexesSum = 0;

    for (int i = 0; i < cardList.size(); i += 1) {
      Card.Figure figureLeft = cardList.get(i).figure;

      for (int j = i + 1; j < cardList.size(); j += 1) {
        Card.Figure figureRight = cardList.get(j).figure;

        if (figureLeft == figureRight) {
          foundIndexesSum += i + j;
          this.pairFigures.add(figureLeft);

          if (this.pairFigures.size() == 2) {
            this.pairFigures.sort(Collections.reverseOrder());
            this.remainingFigure = cardList.get(maxIndexesSum - foundIndexesSum).figure;
            this.winningCondition = this.getWinningCondition(this.pairFigures.get(0), this.pairFigures.get(1));

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
      Card.Figure currentThisPairFigure = this.pairFigures.get(i);
      Card.Figure otherHandPairFigure = otherTwoPairsHand.pairFigures.get(i);

      if (currentThisPairFigure.value > otherHandPairFigure.value) {
        return new CompareResults(Result.WIN, this.winningCondition);
      } else if (currentThisPairFigure.value < otherHandPairFigure.value) {
        return new CompareResults(Result.LOSE, otherHand.winningCondition);
      }
    }

    if (this.remainingFigure.value > otherTwoPairsHand.remainingFigure.value) {
      return new CompareResults(Result.WIN, this.getWinningCondition(this.pairFigures.get(0), this.pairFigures.get(1), this.remainingFigure));
    } else if (this.remainingFigure.value < otherTwoPairsHand.remainingFigure.value) {
      return new CompareResults(Result.LOSE, this.getWinningCondition(otherTwoPairsHand.pairFigures.get(0), otherTwoPairsHand.pairFigures.get(1), otherTwoPairsHand.remainingFigure));
    }

    return new CompareResults(Result.TIE);
  }

  private String getWinningCondition(Card.Figure highestPairFigure, Card.Figure lowestPairFigure) {
    return highestPairFigure.label + " and " + lowestPairFigure.label;
  }
  private String getWinningCondition(Card.Figure highestPairFigure, Card.Figure lowestPairFigure, Card.Figure remainingFigure) {
    return highestPairFigure.label + " and " + lowestPairFigure.label + " with higher card " + remainingFigure.label;
  }
}