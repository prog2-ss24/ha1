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

    // TODO hier weitere Tests erstellen
    @Test // build tool ruft test-methode auf
    @DisplayName("should display result after multiplying two positive numbers") // DisplayName = Beschreibung
    void testPositiveMultiplication() { // testet, ob das richtige Ergebnis angezeigt wird, wenn 2 zahlen multipliziert
                                        // werden
        Calculator calc = new Calculator();

        calc.pressDigitKey(4); // Es wird 4 eingetippt
        calc.pressBinaryOperationKey("x"); // Operator ist x/*, also Multiplikation
        calc.pressDigitKey(1); // 1 eingetippt
        calc.pressDigitKey(2); // 2 eingetippt
        calc.pressEqualsKey(); // = wird eingetippt für das Ergebnis

        String expected = "48"; // 48 wird als Ergebis erwartet
        String actual = calc.readScreen(); // Hier wird das Ergebnis angezeigt beim Calculator

        assertEquals(expected, actual); // Ergbinsse nebeneinander gestellt
    }

    @Test
    @DisplayName("")
    void subWithPercentage() { // subtrahieren mit Prozentsatz
        Calculator calc = new Calculator();

        calc.pressDigitKey(6);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(5);
        calc.pressDigitKey(0);
        calc.pressUnaryOperationKey("%"); // vorher fehlte die Gleichung, um mit Prozenten zu rechnen
        calc.pressEqualsKey();

        String expected = "3"; // (6 - 50% = 3 )
        String actual = calc.readScreen();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("")
    void addKehrwert() { // subtrahieren mit Prozentsatz
        Calculator calc = new Calculator();

        calc.pressDigitKey(6);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("1/x");
        calc.pressEqualsKey(); //Error, weil pressEqualsKey hat keine Operation für 1/x

        String expected = "6.5"; 
        String actual = calc.readScreen();

        assertEquals(expected, actual);

    }
}
