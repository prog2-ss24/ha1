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

    @Test
    void testLimitDisplayToSixteenDigits() {
        Calculator calculator = new Calculator();

        for (int i = 0; i < 17; i++) {

            calculator.pressDigitKey(9);

        }

        String expected = "9999999999999999";
        String actual = calculator.readScreen();

        assertEquals(expected, actual);
    }


    //-------------------------------------------------------------------------------
    @Test
    void testNegativeWithZero() {
        Calculator calculator = new Calculator();

        calculator.pressNegativeKey();


        String expected = "0";
        String actual = calculator.readScreen();

        assertEquals(expected, actual);
    }

}
