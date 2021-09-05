import card.Card;
import hands.*;

import java.util.*;

public class KataPoker {
  public static String whichHandWins(Hand blackHand, Hand whiteHand) {
    Hand.CompareResults compareResults = blackHand.compare(whiteHand);

    if (compareResults.result == Hand.Result.TIE) {
      return "Tie.";
    }

    Map.Entry<String, Hand> blackHandWithColor = new AbstractMap.SimpleEntry<>("Black", blackHand);
    Map.Entry<String, Hand> whiteHandWithColor = new AbstractMap.SimpleEntry<>("White", whiteHand);
    Map.Entry<String, Hand> winningHand = compareResults.result == Hand.Result.WIN ? blackHandWithColor : whiteHandWithColor;

    return winningHand.getKey() + " wins. - with " + winningHand.getValue().label + ": " + compareResults.winningCondition;
  }
}
