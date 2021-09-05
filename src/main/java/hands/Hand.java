package hands;

import card.Card;

import java.util.ArrayList;

public enum Hand {
  STRAIGHT_FLUSH("straight flush"),
  FOUR_OF_A_KIND("four of a kind"),
  FULL_HOUSE("full house"),
  FLUSH("flush"),
  STRAIGHT("straight"),
  THREE_OF_A_KIND("three of a kind"),
  TWO_PAIRS("two pairs"),
  PAIR("pair"),
  HIGH("high card", HighHand::check, HighHand::compare);

  public String label;
  public HandCheck handCheck;
  public HandCompare handCompare;

  Hand(String label, HandCheck handCheck, HandCompare handCompare) {
    this.label = label;
    this.handCheck = handCheck;
    this.handCompare = handCompare;
  }

  public static Hand getHandFromCardDescriptions(String[] cardDescriptions) throws Exception {
    ArrayList<Card> cardList = new ArrayList<>();
    for (String cardDescription : cardDescriptions) {
      cardList.add(new Card(cardDescription));
    }

    boolean hasDuplicateCard = checkIfListHasDuplicateCard(cardList);
    if (hasDuplicateCard) {
      throw new Exception("The hand has a duplicate card, which is not right");
    }

    for (Hand hand : Hand.values()) {
      if (hand.handCheck.check(cardList)) {
        return hand;
      }
    }

    return Hand.HIGH;
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

  public static Hand compareHands(Hand hand1, Hand hand2) {
    int compareHandType = Integer.compare(hand1.ordinal(), hand2.ordinal());
    if (compareHandType > 0) {
      return hand1;
    } else if (compareHandType < 0) {
      return hand2;
    }

    return hand1.handCompare.compare(hand1, hand2);
  }

  @FunctionalInterface
  public interface HandCheck {
    boolean check(ArrayList<Card> cardList);
  }

  @FunctionalInterface
  public interface HandCompare {
    Hand compare(Hand hand1, Hand hand2);
  }
}