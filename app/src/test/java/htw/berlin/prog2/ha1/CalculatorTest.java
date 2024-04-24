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
    @DisplayName("should correctly handle consecutive binary operations like addition and subtraction")
    void testConsecutiveBinaryOperations() {
        Calculator calc = new Calculator();

        // Additionsoperation führt durch
        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();

        // Weiter Subtraktions-Operation
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(2);
        calc.pressEqualsKey();

        // Wünschergbnis ist 5 + 3 - 2 = 6
        String expected = "6";
        String actual = calc.readScreen();

        assertEquals(expected, actual, "The calculator should correctly calculate the result of consecutive addition and subtraction.");
    }
    @Test
    @DisplayName("should correctly add numbers with multiple decimal places")
    void testDecimalAdditionPrecision() {
        Calculator calc = new Calculator();

        // Input: 10.75 + 2.005
        calc.pressDigitKey(1);
        calc.pressDigitKey(0);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDotKey();
        calc.pressDigitKey(0);
        calc.pressDigitKey(0);
        calc.pressDigitKey(5);
        calc.pressEqualsKey();

        // Expected result should be "12.755" but rounded/displayed as "12.76" if screen limit is 10 characters
        String expected = "12.76";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }



    @Test
    @DisplayName("should correctly handle changing operations mid-sequence")
    void testChangingOperationsMidSequence() {
        Calculator calc = new Calculator();

        // Starting with 5 + 3
        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);

        // Change to subtraction before pressing equals
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(2);
        calc.pressEqualsKey();

        // Expected to compute 5 - 2 = 3
        String expected = "3";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

}

