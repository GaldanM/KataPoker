import java.util.ArrayList;

public class KataPoker {
  static Hand createHand(ArrayList<Card> cardList) {
    PairHand pairHand = new PairHand();

    if (pairHand.check(cardList)) {
      return pairHand;
    }

    return new HighHand();
  }
}
