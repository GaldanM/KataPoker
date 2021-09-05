import hands.Hand;

public class Main {
  public Main() {}

  public String execute(String input) throws Exception {
    String trimmedInput = input.trim();
    String[] playerOneCardDescriptions = trimmedInput.substring(7, 21).split(" ");
    String[] playerTwoCardDescriptions = trimmedInput.substring(29).split(" ");

    Hand blackHand = Hand.getHandFromCardDescriptions(playerOneCardDescriptions);
    Hand whiteHand = Hand.getHandFromCardDescriptions(playerTwoCardDescriptions);

    Hand winningHand = Hand.compareHands(blackHand, whiteHand);
    return winningHand.getKey() + " wins. - with " + winningHand.getValue().label + ": " + compareResults.winningCondition;
  }
}
