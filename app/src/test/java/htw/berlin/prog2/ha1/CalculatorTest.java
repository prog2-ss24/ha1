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

    /**
     * Teilaufgabe (zweite Rote Test)
     * Testet, ob der Taschenrechner die vorherige Addition wiederholt, wenn die "=" Taste wiederholt gedrückt wird.
     * Dieser Test initialisiert ein Calculator-Objekt und führt eine Addition von 7 und 2 durch, dann wird die "=" Taste zum ersten Mal gedrückt, was das Ergebnis 9 liefert.
     * Anschließend wird die "=" Taste erneut gedrückt, um zu überprüfen, ob der Taschenrechner die letzte Operation (7 + 2) wiederholt und korrekt das neue Ergebnis 11 berechnet und anzeigt.
     * Erwartet wird, dass das Ergebnis "11" auf dem Bildschirm angezeigt wird, was eine erfolgreiche Wiederholung der vorherigen Operation bestätigen würde.
     */
    @Test
    @DisplayName("Should repeat the previous addition operation multiple times when equals is pressed repeatedly")
    void testRepeatPreviousOperation() {
        Calculator calculator = new Calculator();

        calculator.pressDigitKey(7);
        calculator.pressBinaryOperationKey("+");
        calculator.pressDigitKey(2);
        calculator.pressEqualsKey(); // 7 + 2 = 9
        calculator.pressEqualsKey(); // widederholt Addition: 9 + 2 = 11

        String expected = "11";
        String actual = calculator.readScreen();

        assertEquals(expected, actual);
    }
}

