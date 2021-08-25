import java.util.ArrayList;

public class KataPoker {
  static Hand createHand(ArrayList<Card> cardList) throws Exception {
    boolean hasDuplicateCard = checkIfListHasDuplicateCard(cardList);
    if (hasDuplicateCard) {
      throw new Exception("The hand has a duplicate card, which is not right");
    }

    Hand[] handsHighestToLowest = new Hand[] {
        new StraightFlushHand(),
        new FourOfAKindHand(),
        new FullHouseHand(),
        new FlushHand(),
        new StraightHand(),
        new ThreeOfAKindHand(),
        new TwoPairsHand(),
        new PairHand()
    };
    for (Hand hand: handsHighestToLowest) {
      if (hand.check(cardList)) {
        return hand;
      }
    }

    HighHand highHand = new HighHand();
    highHand.check(cardList);
    return new HighHand();
  }

  private static boolean checkIfListHasDuplicateCard(ArrayList<Card> cardList) {
    for (int i = 0; i < cardList.size(); i += 1) {
      Card cardToCheck = cardList.get(i);

      for (int j = i + 1; j < cardList.size(); j += 1) {
        Card currentCard = cardList.get(j);

        if (currentCard.equals(cardToCheck)) {
          return true;
        }
      }
    }

    return false;
  }
}
