package hands;

import card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PairHand extends Hand {
  public Card.Figure pairFigure;
  public List<Card.Figure> remainingFiguresOrdered;

  public PairHand() {
    this.handType = HandType.PAIR;
    this.name = "pair";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    for (int i = 0; i < cardList.size(); i += 1) {
      Card.Figure figureLeft = cardList.get(i).figure;

      for (int j = i + 1; j < cardList.size(); j += 1) {
        Card.Figure figureRight = cardList.get(j).figure;

        if (figureLeft == figureRight) {
          this.pairFigure = figureLeft;
          this.winningCondition = PairHand.getWinningCondition(figureLeft);

          LinkedList<Card> cardLinkedList = new LinkedList<>(cardList);
          cardLinkedList.remove(i);
          cardLinkedList.remove(j - 1);
          this.remainingFiguresOrdered = List.copyOf(cardLinkedList.stream().map(card -> card.figure).sorted(Collections.reverseOrder()).toList());

          return true;
        }
      }
    }
    return false;
  }

  @Override
  public HandCompare compare(Hand otherHand) {
    if (otherHand.handType == HandType.HIGH) {
      return new HandCompare(HandCompare.Result.WIN, this.winningCondition);
    }

    if (otherHand.handType == this.handType) {
      PairHand otherPairHand = ((PairHand) otherHand);
      Card.Figure otherHandPairFigure = otherPairHand.pairFigure;

      if (this.pairFigure.value > otherHandPairFigure.value) {
        return new HandCompare(HandCompare.Result.WIN, this.winningCondition);
      } else if (this.pairFigure == otherHandPairFigure) {
        return this.checkRemaining(otherPairHand);
      }
    }

    return new HandCompare(HandCompare.Result.LOSE, otherHand.winningCondition);
  }

  private HandCompare checkRemaining(PairHand otherHand) {
    for (int i = 0; i < this.remainingFiguresOrdered.size(); i += 1) {
      Card.Figure currentThisFigure = this.remainingFiguresOrdered.get(i);
      Card.Figure currentOtherHandFigure = otherHand.remainingFiguresOrdered.get(i);

      if (currentThisFigure.value > currentOtherHandFigure.value) {
        return new HandCompare(HandCompare.Result.WIN, PairHand.getWinningCondition(this.pairFigure, currentThisFigure));
      } else if (currentThisFigure.value < currentOtherHandFigure.value) {
        return new HandCompare(HandCompare.Result.LOSE, PairHand.getWinningCondition(otherHand.pairFigure, currentOtherHandFigure));
      }
    }
    return new HandCompare(HandCompare.Result.TIE);
  }

  public static String getWinningCondition(Card.Figure pairFigure) {
    return pairFigure.label;
  }

  public static String getWinningCondition(Card.Figure pairFigure, Card.Figure highestFigureAmongRest) {
    return pairFigure.label + " and high card " + highestFigureAmongRest.label;
  }
}