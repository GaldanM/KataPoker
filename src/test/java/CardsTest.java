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
}
