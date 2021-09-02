package hands;

import card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PairHand extends Hand {
  public Card pairCard;
  public List<Card> remainingCardsOrdered;

  public PairHand() {
    this.handType = HandType.PAIR;
    this.name = "pair";
  }

  @Override
  public boolean check(ArrayList<Card> cardList) {
    for (int i = 0; i < cardList.size(); i += 1) {
      Card cardLeft = cardList.get(i);

      for (int j = i + 1; j < cardList.size(); j += 1) {
        Card cardRight = cardList.get(j);

        if (cardLeft.value == cardRight.value) {
          this.pairCard = cardLeft;
          this.winningCondition = this.getWinningCondition(cardLeft);

          ArrayList<Card> tmp = new ArrayList<>(cardList);
          tmp.remove(i);
          tmp.remove(j - 1);
          this.remainingCardsOrdered = List.copyOf(tmp.stream().sorted(Collections.reverseOrder()).toList());

          return true;
        }
      }
    }
    return false;
  }

  @Override
  public CompareResults compare(Hand otherHand) {
    if (otherHand.handType == HandType.HIGH) {
      return new CompareResults(Result.WIN, this.winningCondition);
    }

    if (otherHand.handType == this.handType) {
      Card otherHandPairCard = ((PairHand) otherHand).pairCard;

      if (this.pairCard.value > otherHandPairCard.value) {
        return new CompareResults(Result.WIN, this.winningCondition);
      } else if (this.pairCard.value == otherHandPairCard.value) {
        List<Card> otherHandValuesSorted = ((PairHand) otherHand).remainingCardsOrdered;

        for (int i = 0; i < this.remainingCardsOrdered.size(); i += 1) {
          Card currentThisCard = this.remainingCardsOrdered.get(i);
          Card currentOtherHandCard = otherHandValuesSorted.get(i);

          if (currentThisCard.value > currentOtherHandCard.value) {
            return new CompareResults(Result.WIN, this.getWinningCondition(this.pairCard, currentThisCard));
          } else if (currentThisCard.value < currentOtherHandCard.value) {
            return new CompareResults(Result.LOSE, this.getWinningCondition(otherHandPairCard, currentOtherHandCard));
          }
        }

        return new CompareResults(Result.TIE);
      }
    }

    return new CompareResults(Result.LOSE, otherHand.winningCondition);
  }

  private String getWinningCondition(Card pairCard) {
    return pairCard.valueToString();
  }

  private String getWinningCondition(Card pairCard, Card highestCard) {
    return pairCard.valueToString() + " and high card " + highestCard.valueToString();
  }
}