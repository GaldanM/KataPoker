package hands;

import card.Card;

import java.util.ArrayList;
import java.util.List;

public class StraightHand extends Hand {
  public StraightHand() {
    this.handType = HandType.STRAIGHT;
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    List<Integer> sortedCardsValues = cardList.stream().map(card -> card.value).sorted().toList();

    for (int i = 0; i < sortedCardsValues.size() - 1; i += 1) {
      int currentValue = sortedCardsValues.get(i);
      int nextValue = sortedCardsValues.get(i + 1);

      if (currentValue != nextValue - 1) {
        return false;
      }
    }

    return true;
  }
}