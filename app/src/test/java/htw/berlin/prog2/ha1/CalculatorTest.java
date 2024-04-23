package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("should display result after subtracting two numbers")
    void testSimpleSubtraction() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();

        String expected = "2";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    /**
     * Testet die Funktionalität des Wiederholens der letzten Operation, wenn die '='-Taste wiederholt gedrückt wird.
     * Überprüft, ob der Taschenrechner die letzte Operation korrekt wiederholt, wenn die '='-Taste mehrmals gedrückt wird.
     */
    @Test
    @DisplayName("should correctly repeat the last operation when '=' is pressed repeatedly")
    void testRepeatLastOperation() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();
        calc.pressEqualsKey(); // Sollte nochmal 3 addieren

        String expected = "11";
        String actual = calc.readScreen();

        assertEquals(expected, actual);

    }
    /**
     * Testet, ob die letzte Operation korrekt wiederholt wird, wenn die "="-Taste wiederholt gedrückt wird.
     * Dabei wird zunächst eine Subtraktion ausgeführt, indem die Ziffern 8 und 2 eingegeben werden und
     * dann zweimal die "="-Taste gedrückt wird. Das Ergebnis wird überprüft, um sicherzustellen, dass
     * es korrekt ist. Der erwartete Wert ist "4", da 8 - 2 = 6 ist und das Ergebnis bei wiederholtem
     * Drücken der "="-Taste erneut um 2 subtrahiert wird.
     */

    @Test
    @DisplayName("should correctly repeat the last operation when '=' is pressed repeatedly")
    void testRepeatLastOperation1() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(8);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(2);
        calc.pressEqualsKey();
        calc.pressEqualsKey();

        String expected = "4";
        String actual = calc.readScreen();

        assertEquals(expected, actual);

    }
    /**
     * Testet, ob die letzte Operation korrekt wiederholt wird, wenn die "="-Taste wiederholt gedrückt wird.
     * Dabei wird zunächst eine Multiplikation ausgeführt, indem die Ziffern 4 und 2 eingegeben werden und
     * dann zweimal die "="-Taste gedrückt wird. Das Ergebnis wird überprüft, um sicherzustellen, dass
     * es korrekt ist. Der erwartete Wert ist "16", da 4 * 2 = 8 ist und das Ergebnis bei wiederholtem
     * Drücken der "="-Taste erneut mit 2 multipliziert wird.
     */
    @Test
    @DisplayName("should correctly repeat the last operation when '=' is pressed repeatedly")
    void testRepeatLastOperation2() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(4);
        calc.pressBinaryOperationKey("x");
        calc.pressDigitKey(2);
        calc.pressEqualsKey();
        calc.pressEqualsKey();

        String expected = "16";
        String actual = calc.readScreen();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("should reset the calculator state when clear key is pressed")
    void testClearKey() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();
        calc.pressClearKey();

    }


    @Test
    @DisplayName("should handle decimals correctly and not exceed screen limit")
    void testDecimalHandling() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(1);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDotKey();
        calc.pressDigitKey(2);
        calc.pressEqualsKey();

        String expected = "3.3"; // Hier darf das Ergebnis nicht mehr als eine Dezimalstelle enthalten.
        String actual = calc.readScreen();

        assertEquals(expected, actual.substring(0, 3));
    }


    @Test
    @DisplayName("should perform square root operation correctly")
    void testSquareRootOperation() {

        Calculator calc = new Calculator();


        calc.pressDigitKey(9);
        calc.pressUnaryOperationKey("√");

        String expected = "3";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should handle percentage operation correctly")
    void testPercentageOperation() {

        Calculator calc = new Calculator();

        calc.pressDigitKey(50);
        calc.pressUnaryOperationKey("%");


        String expected = "0.5";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should perform inversion operation correctly")
    void testInversionOperation() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(4);
        calc.pressUnaryOperationKey("1/x");

        String expected = "0.25";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should handle division by zero correctly in inversion operation")
    void testDivisionByZeroInversion() {

        Calculator calc = new Calculator();


        calc.pressDigitKey(0);
        calc.pressUnaryOperationKey("1/x");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

}

