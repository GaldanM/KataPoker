package hands;

import card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HighHand {
  public List<Card.Figure> figuresSorted;

  public static boolean check(ArrayList<Card> cardList) {
    return true;
  }

  public static HighHand compare(HighHand hand1, HighHand hand2) {
    this.figuresSorted = cardList.stream().sorted(Collections.reverseOrder()).map(card -> card.figure).collect(Collectors.toList());
    this.winningCondition = HighHand.getWinningCondition(this.figuresSorted.get(0));

    List<Card.Figure> otherHandValuesSorted = hand2.figuresSorted;

    for (int i = 0; i < hand1.figuresSorted.size(); i += 1) {
      Card.Figure currentThisFigure = hand1.figuresSorted.get(i);
      Card.Figure currentOtherHandFigure = otherHandValuesSorted.get(i);

      if (currentThisFigure.value > currentOtherHandFigure.value) {
        return new CompareResults(Result.WIN, HighHand.getWinningCondition(currentThisFigure));
      } else if (currentThisFigure.value < currentOtherHandFigure.value) {
        return new CompareResults(Result.LOSE, HighHand.getWinningCondition(currentOtherHandFigure));
      }
    }

    return new CompareResults(Result.TIE);
  }

  public static String getWinningCondition(Card.Figure winningFigure) {
    return winningFigure.label;
  }
}
