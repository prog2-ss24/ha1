package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName ("should display result after subtraction two positive multi-digit numbers")
    void testSubtraktion(){
        Calculator calc = new Calculator();

        calc.pressDigitKey(3);
        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(2);
        calc.pressDigitKey(4);
        calc.pressEqualsKey();

        String expected = "11";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    //Teil 2
    @Test
    @DisplayName ("should display result after getting adding same positive multi-digit numbers with an other ")
    void testMoreNumbers() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(8);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();

        String expected = "18";
        String actual = calc.readScreen();

        assertEquals(expected, actual); //Zum Überprüfen, ob der erwartete Wert einer Variable dem tatsächlichen Wert entspricht
    }
    @Test
    @DisplayName("should display result after  multiplying a decimal number with a whole number ")
    void testDezimalWithGanzzahl() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(0);
        calc.pressDotKey();
        calc.pressDigitKey(2);
        calc.pressBinaryOperationKey("x");
        calc.pressDigitKey(1);
        calc.pressDigitKey(0);
        calc.pressDigitKey(0);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "200";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


    //TODO hier weitere Tests erstellen

}


