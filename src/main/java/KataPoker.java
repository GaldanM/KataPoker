import card.Card;
import hands.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KataPoker {
  public static Hand createHand(String[] cardDescriptions) throws Exception {
    ArrayList<Card> cardList = new ArrayList<>();
    for (String cardDescription : cardDescriptions) {
      cardList.add(new Card(cardDescription));
    }


    if (checkIfListHasDuplicateCard(cardList)) {
      throw new Exception("The hand has a duplicate card, which is not right");
    }

    Hand[] handsHighestToLowest = new Hand[]{
        new StraightFlushHand(),
        new FourOfAKindHand(),
        new FullHouseHand(),
        new FlushHand(),
        new StraightHand(),
        new ThreeOfAKindHand(),
        new TwoPairsHand(),
        new PairHand()
    };
    for (Hand hand : handsHighestToLowest) {
      if (hand.check(cardList)) {
        return hand;
      }
    }

    HighHand highHand = new HighHand();
    highHand.check(cardList);
    return highHand;
  }

  private static boolean checkIfListHasDuplicateCard(ArrayList<Card> cardList) {
    return cardList.stream()
        .collect(Collectors.groupingBy(Function.identity(),
            Collectors.counting()
        )).entrySet().stream()
        .anyMatch(e -> e.getValue() >= 2);
  }

  public static String whichHandWins(Hand blackHand, Hand whiteHand) {
    Hand.HandCompare handCompare = blackHand.compare(whiteHand);

    if (handCompare.result == Hand.HandCompare.Result.TIE) {
      return "Tie.";
    }

    Map.Entry<String, Hand> blackHandWithColor = new AbstractMap.SimpleEntry<>("Black", blackHand);
    Map.Entry<String, Hand> whiteHandWithColor = new AbstractMap.SimpleEntry<>("White", whiteHand);
    Map.Entry<String, Hand> winningHand = handCompare.result == Hand.HandCompare.Result.WIN ? blackHandWithColor : whiteHandWithColor;

    return winningHand.getKey() + " wins. - with " + winningHand.getValue().name + ": " + handCompare.winningCondition;
  }
}
