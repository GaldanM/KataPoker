package hands;

import card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HighHand extends Hand {
  public List<Card> cardsSorted;

  public HighHand() {
    this.handType = HandType.HIGH;
    this.name = "high card";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    this.cardsSorted = cardList.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
    this.winningCondition = this.getWinningCondition(this.cardsSorted.get(0));

    return true;
  }

  @Override
  public CompareResults compare(Hand otherHand) {
    if (otherHand.handType != this.handType) {
      return new CompareResults(Result.LOSE, otherHand.winningCondition);
    }

    List<Card> otherHandValuesSorted = ((HighHand) otherHand).cardsSorted;

    for (int i = 0; i < this.cardsSorted.size(); i += 1) {
      Card currentThisCard = this.cardsSorted.get(i);
      Card currentOtherHandCard = otherHandValuesSorted.get(i);

      if (currentThisCard.value > currentOtherHandCard.value) {
        return new CompareResults(Result.WIN, this.getWinningCondition(currentThisCard));
      } else if (currentThisCard.value < currentOtherHandCard.value) {
        return new CompareResults(Result.LOSE, this.getWinningCondition(currentOtherHandCard));
      }
    }

    return new CompareResults(Result.TIE);
  }

  private String getWinningCondition(Card winningCard) {
    return winningCard.valueToString();
  }
}
