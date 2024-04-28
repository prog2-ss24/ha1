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


    //TODO hier weitere Tests erstellen
    /**
     * Teilaufgabe 1 (Grüne Test)Testet die Funktionalität des Calculators für die Multiplikation zweier positiver mehrstelliger Zahlen.
     */
    @Test
    @DisplayName("should display result after multiply two positive multi-digit numbers")
    void testPositiveMultipliation() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("x");
        calc.pressDigitKey(1);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "100";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    /**
     *  Teilaufgabe2 (RoteTest)Testet die Funktionalität der Calculator-Klasse für die Division durch Null.
     *  Durch if(screen.equals("Infinity")) screen = "Error"; habe ich die code fixert
     */
    @Test
    @DisplayName("should display result after division by zero")
    void testInversionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(0);
        calc.pressUnaryOperationKey("1/x");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    /**
     * Teilaufgabe2 (RoteTest) Testet die Funktionalität des Calculators für die Quadratwurzel einer positiven mehrstelligen Zahl.
     * durch if(screen.endsWith(".0")) screen = screen.substring(0,screen.length()-2); habe ich die code fixert
     */
    @Test
    @DisplayName("should display result after taking the square root of a positive multi-digit number")
    void testASquareRoot() {
        Calculator calc = new Calculator();


        calc.pressDigitKey(4);
        calc.pressUnaryOperationKey("√");

        String expected = "2";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

}

