import java.util.ArrayList;

public class KataPoker {
  static Hand createHand(ArrayList<Card> cardList) {
    Hand[] handsHighestToLowest = new Hand[] {
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
}
