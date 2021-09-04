import card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

public class CardsTest {
    @ParameterizedTest
    @EnumSource(Card.Suite.class)
    void createCardsSuite(Card.Suite suite) throws Exception {
        String cardDescription = "2" + suite.initial;

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
    @CsvSource({ "T,10,10", "J,11,Jack", "Q,12,Queen", "K,13,King", "A,14,Ace" })
    void createCardsFigure(String initial, String expectedValue, String expectedLabel) throws Exception {
        String cardDescription = initial + "H";

        Card card = new Card(cardDescription);

        Assertions.assertThat(card.figure.value).isEqualTo(Integer.parseInt(expectedValue));
        Assertions.assertThat(card.figure.label).isEqualTo(expectedLabel);
    }

    @Test
    void createCardOne() {
        String cardDescription = "1H";

        Throwable thrown = Assertions.catchThrowable(() -> new Card(cardDescription));

        Assertions.assertThat(thrown).isInstanceOf(Exception.class);
    }
}
