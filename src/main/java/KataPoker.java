import card.Card;
import hands.*;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class KataPoker {
  static Hand createHand(String[] cardDescriptions) throws Exception {
    ArrayList<Card> cardList = new ArrayList<>();
    for (String cardDescription : cardDescriptions) {
      cardList.add(new Card(cardDescription));
    }

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
    return highHand;
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

  public static String whichHandWins(Hand blackHand, Hand whiteHand) {
    Hand.CompareResults compareResults = blackHand.compare(whiteHand);

    if (compareResults.result == Hand.Result.TIE) {
      return "Tie.";
    }

    Map.Entry<String, Hand> blackHandWithColor = new AbstractMap.SimpleEntry<>("Black", blackHand);
    Map.Entry<String, Hand> whiteHandWithColor = new AbstractMap.SimpleEntry<>("White", whiteHand);
    Map.Entry<String, Hand> winningHand = compareResults.result == Hand.Result.WIN ? blackHandWithColor : whiteHandWithColor;

    return winningHand.getKey() + " wins. - with " + winningHand.getValue().name + ": " + compareResults.winningCondition;
  }
}
