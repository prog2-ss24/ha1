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
        System.out.println("testPositiveAddition()");
        System.out.println("Ausgerechnetes Ergebnis: " + actual);
        System.out.println("Erwartetes Ergebnis: " + expected);
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
        System.out.println("testSquareRoot()");
        System.out.println("Ausgerechnetes Ergebnis: " + actual);
        System.out.println("Erwartetes Ergebnis: " + expected);
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
        System.out.println("testDivisionByZero");
        System.out.println("Ausgerechnetes Ergebnis: " + actual);
        System.out.println("Erwartetes Ergebnis: " + expected);
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
        System.out.println("testSquareRootOfNegative()");
        System.out.println("Ausgerechnetes Ergebnis: " + actual);
        System.out.println("Erwartetes Ergebnis: " + expected);
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
        System.out.println("testMultipleDecimalDots");
        System.out.println("Ausgerechnetes Ergebnis: " + actual);
        System.out.println("Erwartetes Ergebnis: " + expected);
    }


    //TODO hier weitere Tests erstellen
    @Test
    @DisplayName("Soll eine Zahl von einer anderen Abziehen")
    void testSubtract() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(1);
        calc.pressEqualsKey();

        String expected = "1";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
        System.out.println("testSubtract()");
        System.out.println("Ausgerechnetes Ergebnis: " + actual);
        System.out.println("Erwartetes Ergebnis: " + expected);
    }

    @Test
    @DisplayName("Soll mehr als nur zwei Zahlen zusammenrechnen.")
    void testSubtractAndMultiply() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(1);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(4);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(4);
        calc.pressEqualsKey();

        String expected = "9";
        String actual = calc.readScreen();
        assertEquals(expected, actual);
        System.out.println("testSubtractAndMultiply()");
        System.out.println("Ausgerechnetes Ergebnis: " + actual);
        System.out.println("Erwartetes Ergebnis: " + expected);
    }

    @Test
    @DisplayName("Soll punkt vor Strich ausrechnen")
    void punktVorStrichTest() {
        Calculator calc = new Calculator();

        calc.pressNegativeKey();
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(3);
        calc.pressBinaryOperationKey("x");
        calc.pressDigitKey(4);
        calc.pressEqualsKey();

        String expected = "8";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
        System.out.println("punktVorStrichTest()");
        System.out.println("Ausgerechnetes Ergebnis: " + actual);
        System.out.println("Erwartetes Ergebnis: " + expected);
    }

}

