import hands.Hand;

public class Main {
  public Main() {}

  public String execute(String input) throws Exception {
    String trimmedInput = input.trim();
    Hand blackHand = KataPoker.createHand(trimmedInput.substring(7, 21).split(" "));
    Hand whiteHand = KataPoker.createHand(trimmedInput.substring(29).split(" "));

    return KataPoker.whichHandWins(blackHand, whiteHand);
  }
}
