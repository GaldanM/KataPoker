import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StraightFlushHand extends Hand {
  public StraightFlushHand() {
    this.handType = HandType.STRAIGHT_FLUSH;
  }

  @Override
  boolean check(ArrayList<Card> cardList) {
    boolean isStraight = new StraightHand().check(cardList);
    boolean isFlush = new FlushHand().check(cardList);

    return isStraight && isFlush;
  }
}