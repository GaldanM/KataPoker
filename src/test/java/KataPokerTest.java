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
}
