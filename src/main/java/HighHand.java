import java.util.ArrayList;

public class HighHand extends Hand {
  public HighHand() {
    this.handType = HandType.HIGH;
  }

  @Override
  boolean check(ArrayList<Card> cardList) {
    return true;
  }
}
