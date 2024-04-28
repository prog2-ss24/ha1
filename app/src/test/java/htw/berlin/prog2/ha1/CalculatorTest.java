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
    //Teilaufgabe 1:Testet eine bisher ungetestete, aber funktionierende Funktionalität des Taschenrechners.

    @Test
    @DisplayName("sollte den Kehrwert einer Zahl ungleich Null anzeigen")
    void testKehrwert() {
        Calculator calc = new Calculator();


        calc.pressDigitKey(5);
        calc.pressUnaryOperationKey("1/x");

        String expected = "0.2";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    //Teilaufgabe 2: Schreiben Sie zwei weitere zusätzliche Tests, die zwei unterschiedliche Fehlerkategorien aufdecken (d.h. deren Fehlerursachen in unterschiedlichen Methoden liegen) und somit fehlschlagen.
    @Test
    @DisplayName("Drücken ClearKey gefolgt von der Gleich-Taste sollte Ergebnis eine Null anzeigen, was jedoch nicht der Fall ist.") // will fail
    void pressClear() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(1); // Ändere von 10 auf 1, da 10 ungültig ist.
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(5);
        calc.pressClearKey();
        calc.pressEqualsKey();

        String expected = "0";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Beim Versuch,null umzukehren, sollte ein Fehler angezeigt werden")
    void testInversionOfZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(0);
        calc.pressUnaryOperationKey("1/x");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Beim Berechnen der Quadratwurzel einer negativen Zahl sollte ein Fehler erscheinen.")
    void testSquareRootAfterNegativeResult() {
        Calculator calc = new Calculator();

        // Negative Zahl erzeugen durch eine einfache Subtraktion
        calc.pressDigitKey(3);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(5);
        calc.pressEqualsKey(); // Ergebnis ist -2

        // Versuch, die Quadratwurzel des negativen Ergebnisses zu ziehen
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual); // Erwartet wird, dass "Error" angezeigt wird
    }


}