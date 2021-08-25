import java.util.ArrayList;

public class FourOfAKindHand extends Hand {
  public FourOfAKindHand() {
    this.handType = HandType.FOUR_OF_A_KIND;
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    for (int i = 0; i < cardList.size(); i += 1) {
      Card cardLeft = cardList.get(i);

      for (int j = i + 1, countKind = 1; j < cardList.size(); j += 1) {
        Card cardRight = cardList.get(j);

        if (cardLeft.value == cardRight.value) {
          countKind += 1;

          if (countKind == 4) {
            return true;
          }
        }
      }
    }

    return false;
  }
}