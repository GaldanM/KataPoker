package hands;

import card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HighHand extends Hand {
  public List<Card.Figure> figuresSorted;

  public HighHand() {
    this.handType = HandType.HIGH;
    this.name = "high card";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    this.figuresSorted = cardList.stream().sorted(Collections.reverseOrder()).map(card -> card.figure).collect(Collectors.toList());
    this.winningCondition = HighHand.getWinningCondition(this.figuresSorted.get(0));

    return true;
  }

  @Override
  public HandCompare compare(Hand otherHand) {
    if (otherHand.handType != this.handType) {
      return new HandCompare(HandCompare.Result.LOSE, otherHand.winningCondition);
    }

    List<Card.Figure> otherHandValuesSorted = ((HighHand) otherHand).figuresSorted;

    for (int i = 0; i < this.figuresSorted.size(); i += 1) {
      Card.Figure currentThisFigure = this.figuresSorted.get(i);
      Card.Figure currentOtherHandFigure = otherHandValuesSorted.get(i);

      if (currentThisFigure.value > currentOtherHandFigure.value) {
        return new HandCompare(HandCompare.Result.WIN, HighHand.getWinningCondition(currentThisFigure));
      } else if (currentThisFigure.value < currentOtherHandFigure.value) {
        return new HandCompare(HandCompare.Result.LOSE, HighHand.getWinningCondition(currentOtherHandFigure));
      }
    }

    return new HandCompare(HandCompare.Result.TIE);
  }

  public static String getWinningCondition(Card.Figure winningFigure) {
    return winningFigure.label;
  }
}
