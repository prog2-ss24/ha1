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
     * teilaufgabe 1 grüneTest
     * Löscht den aktuellen Eintrag auf dem Taschenrechner, ohne den Taschenrechnerzustand zurückzusetzen.
     *
     * @return
     * Gibt den Bildschirminhalt nach dem Löschen des Eintrags als String zurück. Erwartet wird, dass der Bildschirm "0" anzeigt.
     */

    @Test
    @DisplayName("should clear current entry without resetting the calculator state")
    void testClearEntry() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);
        calc.pressClearKey(); // Clear Entry

        String expected = "0"; // Expecting the screen to show "0" after clearing entry
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    /**
     * Teilaufgabe2 RoteTest
     * Überprüft, ob das Ergebnis nach der Berechnung der Quadratwurzel einer Zahl ohne Dezimalpunkt angezeigt wird.
     *
     *
     * @return
     * Gibt den Bildschirminhalt nach der Quadratwurzelberechnung als String zurück. Erwartet wird eine ganze Zahl ohne Dezimalpunkt.
     */

    @Test
    @DisplayName("should display the result after getting the square root of a number but without a decimal point")

        void testASquareRoot() {
            Calculator calc = new Calculator();


            calc.pressDigitKey(1);
            calc.pressDigitKey(6);
            calc.pressUnaryOperationKey("√");

            String expected = "4";
            String actual = calc.readScreen();

            assertEquals(expected, actual);

}}

