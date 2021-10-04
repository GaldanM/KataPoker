import card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.*;

public class CardsTest {

    //@Test
   /* void createCard() {
        assertThat(Card.from("2C")).isEqualTo(new Card(Card.Figure.TWO, Card.Suite.CLUB))
    }
    */
    @ParameterizedTest
    @EnumSource(Card.Suite.class)
    void createCardsSuite(Card.Suite suite) throws Exception {
        String cardDescription = "2" + suite.initial;

        Card card = new Card(cardDescription);

        assertThat(card.suite).isEqualTo(suite);
    }

    @Test
    void createCardWrongSymbol() {
        String wrongCard = "2G";

        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> new Card(wrongCard));
    }

    @ParameterizedTest
    @CsvSource({ "T,10,10", "J,11,Jack", "Q,12,Queen", "K,13,King", "A,14,Ace" })
    void createCardsFigure(String initial, String expectedValue, String expectedLabel) throws Exception {
        String cardDescription = initial + "H";

        Card card = new Card(cardDescription);
        

        assertThat(card.figure.value).isEqualTo(Integer.parseInt(expectedValue));
        assertThat(card.figure.label).isEqualTo(expectedLabel);
    }

    @Test
    void createCardOne() {
        String cardDescription = "1H";

        Throwable thrown = Assertions.catchThrowable(() -> new Card(cardDescription));

        assertThat(thrown).isInstanceOf(Exception.class);
    }
}
