import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.List;

public class KataPokerTest {
    @ParameterizedTest
    @EnumSource(Card.Suite.class)
    void createCardsSuite(Card.Suite suite) throws Exception {
        String cardDescription = "2" + suite.label;

        Card card = new Card(cardDescription);

        Assertions.assertThat(card.suite).isEqualTo(suite);
    }

    @Test
    void createCardWrongSymbol() {
        String cardDescription = "2G";

        Throwable thrown = Assertions.catchThrowable(() -> new Card(cardDescription));

        Assertions.assertThat(thrown).isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @CsvSource({ "T,10", "J,11", "Q,12", "K,13", "A,14" })
    void createCardsFigure(String figureChar, String expectedValue) throws Exception {
        String cardDescription = figureChar + "H";

        Card card = new Card(cardDescription);

        Assertions.assertThat(card.value).isEqualTo(Integer.parseInt(expectedValue));
        Assertions.assertThat(card.suite).isEqualTo(Card.Suite.HEART);
    }

    @Test
    void createCardOne() {
        String cardDescription = "1H";

        Throwable thrown = Assertions.catchThrowable(() -> new Card(cardDescription));

        Assertions.assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    void createHighHand() throws Exception {
        ArrayList<Card> cardList = new ArrayList<>(
            List.of(
                new Card("2H"),
                new Card("3D"),
                new Card("6S"),
                new Card("9C"),
                new Card("KD")
            ));

        Hand hand = KataPoker.createHand(cardList);

        Assertions.assertThat(hand.handType).isEqualTo(Hand.HandType.HIGH);
    }

    @Test
    void createPairHand() throws Exception {
        ArrayList<Card> cardList = new ArrayList<>(
            List.of(
                new Card("2H"),
                new Card("3D"),
                new Card("9S"),
                new Card("9C"),
                new Card("KD")
            ));

        Hand hand = KataPoker.createHand(cardList);

        Assertions.assertThat(hand.handType).isEqualTo(Hand.HandType.PAIR);
    }

    @Test
    void createTwoPairsHand() throws Exception {
        ArrayList<Card> cardList = new ArrayList<>(
            List.of(
                new Card("3H"),
                new Card("3D"),
                new Card("9S"),
                new Card("9C"),
                new Card("KD")
            ));

        Hand hand = KataPoker.createHand(cardList);

        Assertions.assertThat(hand.handType).isEqualTo(Hand.HandType.TWO_PAIRS);
    }

    @Test
    void createThreeOfAKindHand() throws Exception {
        ArrayList<Card> cardList = new ArrayList<>(
            List.of(
                new Card("3H"),
                new Card("3D"),
                new Card("3S"),
                new Card("9C"),
                new Card("KD")
            ));

        Hand hand = KataPoker.createHand(cardList);

        Assertions.assertThat(hand.handType).isEqualTo(Hand.HandType.THREE_OF_A_KIND);
    }

    @Test
    void createStraightHand() throws Exception {
        ArrayList<Card> cardList = new ArrayList<>(
            List.of(
                new Card("3H"),
                new Card("5D"),
                new Card("4S"),
                new Card("6C"),
                new Card("2D")
            ));

        Hand hand = KataPoker.createHand(cardList);

        Assertions.assertThat(hand.handType).isEqualTo(Hand.HandType.STRAIGHT);
    }

    @Test
    void createFlushHand() throws Exception {
        ArrayList<Card> cardList = new ArrayList<>(
            List.of(
                new Card("2D"),
                new Card("KD"),
                new Card("6D"),
                new Card("TD"),
                new Card("9D")
            ));

        Hand hand = KataPoker.createHand(cardList);

        Assertions.assertThat(hand.handType).isEqualTo(Hand.HandType.FLUSH);
    }

    @Test
    void createFullHouseHand() throws Exception {
        ArrayList<Card> cardListLowestIsTriple = new ArrayList<>(
            List.of(
                new Card("2D"),
                new Card("KD"),
                new Card("2C"),
                new Card("KS"),
                new Card("2S")
            ));
        ArrayList<Card> cardListHighestIsTriple = new ArrayList<>(
            List.of(
                new Card("KD"),
                new Card("2D"),
                new Card("KC"),
                new Card("2S"),
                new Card("KS")
            ));

        Hand handLowestIsTriple = KataPoker.createHand(cardListLowestIsTriple);
        Hand handHighestIsTriple = KataPoker.createHand(cardListHighestIsTriple);

        Assertions.assertThat(handLowestIsTriple.handType).isEqualTo(Hand.HandType.FULL_HOUSE);
        Assertions.assertThat(handHighestIsTriple.handType).isEqualTo(Hand.HandType.FULL_HOUSE);
    }
}
