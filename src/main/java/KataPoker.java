import java.util.ArrayList;

public class KataPoker {
  static Hand createHand(ArrayList<Card> cardList) {
    TwoPairsHand twoPairsHand = new TwoPairsHand();
    PairHand pairHand = new PairHand();

    if (twoPairsHand.check(cardList)) {
      return twoPairsHand;
    }

    if (pairHand.check(cardList)) {
      return pairHand;
    }

    return new HighHand();
  }
}
