package hands;

import card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FullHouseHand extends Hand {
  public FullHouseHand() {
    this.handType = HandType.FULL_HOUSE;
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    List<Card> cardListSorted = cardList.stream().sorted().collect(Collectors.toList());

    boolean firstTwoAreTheSame = cardListSorted.get(0).value == cardListSorted.get(1).value;
    boolean lastTwoAreTheSame = cardListSorted.get(3).value == cardListSorted.get(4).value;

    if (firstTwoAreTheSame && lastTwoAreTheSame) {
      boolean isFirstTwoTheTriple = cardListSorted.get(2).value == cardListSorted.get(0).value;
      boolean isLastTwoTheTriple = cardListSorted.get(2).value == cardListSorted.get(4).value;

      return isFirstTwoTheTriple || isLastTwoTheTriple;
    }

    return false;
  }
}
