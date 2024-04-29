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

        assertEquals(expected, actual.replaceAll(",", ".")); // Ersetze das Komma durch einen Punkt
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
    @DisplayName("should correctly change the sign of a number")
    void testChangeSign() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(5);
        calc.pressNegativeKey(); // Erwartet: -5
        String expected = "-5";
        String actual = calc.readScreen();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should handle decimal points correctly after an operation")
    void testDecimalHandlingAfterOperation() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(9);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(1);
        calc.pressEqualsKey(); // Erstes Ergebnis: 10
        calc.pressDigitKey(2);
        calc.pressDotKey();
        calc.pressDigitKey(5); // Neue Eingabe: 2.5
        String expected = "2.5";
        String actual = calc.readScreen();
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Test multiple digit input followed by a decimal point")
    public void testMultipleDigitsAndDecimalPoint() {
        Calculator calc = new Calculator();  // Erstellen Sie eine Instanz des Taschenrechners

        // Drücken Sie sechsmal die Zifferntaste "1"
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);


        // Drücken Sie die Dezimalpunkttaste
        calc.pressDotKey();

        // Erwarteter Bildschirmwert nach dem Drücken des Dezimalpunkts
        String expected = "111111111.";
        String actual = calc.readScreen();

        assertEquals(expected, actual, "The screen should display '111111111.' after entering six ones followed by a decimal point");
    }

}
