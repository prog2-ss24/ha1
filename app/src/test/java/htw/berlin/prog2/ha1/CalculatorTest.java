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
    @DisplayName("should display result after multiplying two numbers")
    void testMultiplication() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(3);
        calc.pressBinaryOperationKey("x");
        calc.pressDigitKey(4);
        calc.pressEqualsKey();

        String expected = "12";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

// Teilaufgabe 2.1
@Test
@DisplayName("should display latest number after pressing equals")
void testEqualKey() {
    Calculator calc = new Calculator();
    calc.pressDigitKey(3);
    calc.pressEqualsKey();

    String expected = "3";
    String actual = calc.readScreen();

    assertEquals(expected, actual);

}
    // Teiaufgabe 2.2

    @Test
    @DisplayName("should display error when dividing zero by zero")
    void testZeroDivision() {
        // Arrange
        Calculator calc = new Calculator();

        // Act
        calc.pressDigitKey(0); // Eingabe der ersten Zahl
        calc.pressBinaryOperationKey("/"); // Auswahl der Division
        calc.pressDigitKey(0); // Eingabe der zweiten Zahl
        calc.pressEqualsKey(); // Ausführen der Berechnung

        // Assert
        String expected = "Error";
        String actual = calc.readScreen();
        assertEquals(expected, actual);
    }



    //TODO hier weitere Tests erstellen

}

