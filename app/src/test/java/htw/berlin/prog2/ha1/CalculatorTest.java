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

    /**
     * Testet die Funktionalität der einfachen Subtraktion zweier Zahlen.
     * Überprüft, ob der Taschenrechner das Ergebnis der Subtraktion korrekt anzeigt.
     */
    @Test
    @DisplayName("should display result after subtracting two numbers")
    void testSimpleSubtraction() {
        // Testfall initialisieren
        Calculator calc = new Calculator();

        // Operationen durchführen
        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();

        // Erwartetes und tatsächliches Ergebnis vergleichen
        String expected = "2";
        String actual = calc.readScreen();
        assertEquals(expected, actual);
    }

    /**
     * Testet die Funktionalität des Wiederholens der letzten Operation bei wiederholtem Drücken der '='-Taste.
     * Überprüft, ob der Taschenrechner die letzte Operation korrekt wiederholt, wenn die '='-Taste mehrmals gedrückt wird.
     */
    @Test
    @DisplayName("should correctly repeat the last operation when '=' is pressed repeatedly")
    void testRepeatLastOperation() {
        // Testfall initialisieren
        Calculator calc = new Calculator();

        // Operationen durchführen
        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();
        calc.pressEqualsKey(); // Sollte nochmal 3 addieren

        // Erwartetes und tatsächliches Ergebnis vergleichen
        String expected = "11";
        String actual = calc.readScreen();
        assertEquals(expected, actual);
    }

    /**
     * Testet, ob die letzte Operation korrekt wiederholt wird, wenn die "="-Taste wiederholt gedrückt wird.
     * Überprüft, ob die letzte Operation wiederholt wird, wenn die "="-Taste wiederholt gedrückt wird.
     */
    @Test
    @DisplayName("should correctly repeat the last operation when '=' is pressed repeatedly")
    void testRepeatLastOperation1() {
        // Testfall initialisieren
        Calculator calc = new Calculator();

        // Operationen durchführen
        calc.pressDigitKey(8);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(2);
        calc.pressEqualsKey();
        calc.pressEqualsKey();

        // Erwartetes und tatsächliches Ergebnis vergleichen
        String expected = "4";
        String actual = calc.readScreen();
        assertEquals(expected, actual);
    }

    /**
     * Testet, ob die letzte Operation korrekt wiederholt wird, wenn die "="-Taste wiederholt gedrückt wird.
     * Überprüft, ob die letzte Operation wiederholt wird, wenn die "="-Taste wiederholt gedrückt wird.
     */
    @Test
    @DisplayName("should correctly repeat the last operation when '=' is pressed repeatedly")
    void testRepeatLastOperation2() {
        // Testfall initialisieren
        Calculator calc = new Calculator();

        // Operationen durchführen
        calc.pressDigitKey(4);
        calc.pressBinaryOperationKey("x");
        calc.pressDigitKey(2);
        calc.pressEqualsKey();
        calc.pressEqualsKey();

        // Erwartetes und tatsächliches Ergebnis vergleichen
        String expected = "16";
        String actual = calc.readScreen();
        assertEquals(expected, actual);
    }

    /**
     * Testet die Zurücksetzung des Taschenrechnerzustands, wenn die "Clear"-Taste gedrückt wird.
     * Überprüft, ob der Taschenrechnerzustand korrekt zurückgesetzt wird, wenn die "Clear"-Taste gedrückt wird.
     */
    @Test
    @DisplayName("should reset the calculator state when clear key is pressed")
    void testClearKey() {
        // Testfall initialisieren
        Calculator calc = new Calculator();

        // Operationen durchführen
        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();
        calc.pressClearKey();
    }

    /**
     * Testet die Behandlung von Dezimalzahlen und stellt sicher, dass der Bildschirm die Grenze nicht überschreitet.
     * Überprüft, ob der Taschenrechner Dezimalzahlen korrekt behandelt und den Bildschirm nicht über die Grenze hinaus erweitert.
     */
    @Test
    @DisplayName("should handle decimals correctly and not exceed screen limit")
    void testDecimalHandling() {
        // Testfall initialisieren
        Calculator calc = new Calculator();

        // Operationen durchführen
        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(1);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDotKey();
        calc.pressDigitKey(2);
        calc.pressEqualsKey();

        // Erwartetes und tatsächliches Ergebnis vergleichen
        String expected = "3.3"; // Hier darf das Ergebnis nicht mehr als eine Dezimalstelle enthalten.
        String actual = calc.readScreen();
        assertEquals(expected, actual.substring(0, 3)); // Nur die ersten drei Zeichen vergleichen
    }

    /**
     * Testet die Quadratwurzeloperation und stellt sicher, dass das Ergebnis korrekt ist.
     * Überprüft, ob die Quadratwurzeloperation korrekt durchgeführt wird und das Ergebnis angezeigt wird.
     */
    @Test
    @DisplayName("should perform square root operation correctly")
    void testSquareRootOperation() {
        // Testfall initialisieren
        Calculator calc = new Calculator();

        // Operationen durchführen
        calc.pressDigitKey(9);
        calc.pressUnaryOperationKey("√");

        // Erwartetes und tatsächliches Ergebnis vergleichen
        String expected = "3";
        String actual = calc.readScreen();
        assertEquals(expected, actual);
    }

    /**
     * Testet die Prozentoperation und stellt sicher, dass das Ergebnis korrekt ist.
     * Überprüft, ob die Prozentoperation korrekt durchgeführt wird und das Ergebnis angezeigt wird.
     */
    @Test
    @DisplayName("should handle percentage operation correctly")
    void testPercentageOperation() {
        // Testfall initialisieren
        Calculator calc = new Calculator();

        // Operationen durchführen
        calc.pressDigitKey(50);
        calc.pressUnaryOperationKey("%");

        // Erwartetes und tatsächliches Ergebnis vergleichen
        String expected = "0.5";
        String actual = calc.readScreen();
        assertEquals(expected, actual);
    }

    /**
     * Testet die Inversionsoperation und stellt sicher, dass das Ergebnis korrekt ist.
     * Überprüft, ob die Inversionsoperation korrekt durchgeführt wird und das Ergebnis angezeigt wird.
     */
    @Test
    @DisplayName("should perform inversion operation correctly")
    void testInversionOperation() {
        // Testfall initialisieren
        Calculator calc = new Calculator();

        // Operationen durchführen
        calc.pressDigitKey(4);
        calc.pressUnaryOperationKey("1/x");

        // Erwartetes und tatsächliches Ergebnis vergleichen
        String expected = "0.25";
        String actual = calc.readScreen();
        assertEquals(expected, actual);
    }

    /**
     * Testet die Behandlung der Division durch Null in der Inversionsoperation und stellt sicher, dass das Ergebnis korrekt ist.
     * Überprüft, ob die Inversionsoperation korrekt auf eine Division durch Null reagiert und "Error" anzeigt.
     */
    @Test
    @DisplayName("should handle division by zero correctly in inversion operation")
    void testDivisionByZeroInversion() {
        // Testfall initialisieren
        Calculator calc = new Calculator();

        // Operationen durchführen
        calc.pressDigitKey(0);
        calc.pressUnaryOperationKey("1/x");

        // Erwartetes und tatsächliches Ergebnis vergleichen
        String expected = "Error";
        String actual = calc.readScreen();
        assertEquals(expected, actual);
    }


}

