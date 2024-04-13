package htw.berlin.prog2.ha1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class JCalculatorTest {

    @Test
    void testChangeSignPositiveToNegatie() {
        Calculator calculator = new Calculator();

        calculator.pressDigitKey(9);
        calculator.pressNegativeKey();

        String expected = "-9";
        String actual = calculator.readScreen();

        assertEquals(expected, actual);
    }

}
