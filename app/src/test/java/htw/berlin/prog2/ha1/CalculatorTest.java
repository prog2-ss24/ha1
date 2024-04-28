package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
public CalculatorTest {

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

        String expected = "4000";
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
    @DisplayName("should display result after subtracting two positive multi-digit numbers")
    void testNegativeSubstraction(){
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBineryOperationKey("-");
        calc.pressDigitKey(1);
        calc.pressDigitKey(0);

        String expected = "10"
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
public CalculatorTest {

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
        @DisplayName("should display result after subtracting two positive multi-digit numbers")
        void testNegativeSubstraction(){
            Calculator calc = new Calculator();

            calc.pressDigitKey(2);
            calc.pressDigitKey(0);
            calc.pressBinaryOperationKey("-");
            calc.pressDigitKey(1);
            calc.pressDigitKey(0);
            calc.pressEqualsKey();

            String expected = "10";
            String actual = calc.readScreen();

            assertEquals(expected, actual);
        }
        @Test
        @DisplayName("should finish the first Operation before going on with the second one")
        void testTwoOperations() {
            Calculator calc = new Calculator();

            calc.pressDigitKey(1);
            calc.pressBinaryOperationKey('+');
            calc.pressDigitKey(2);
            calc.pressBinaryOperationKey('*');
            calc.pressDigitKey(2);
            calc.pressEqualsKey();

            String expected = "6";
            String actual = calc.readScreen();

            assertEquals(expected, actual);
        }
        @Test
        @DisplayName("should repeat the last operation again on the new result by pressing equals key")
        void testPressEqualsKeyDouble() {
            Calculator calc = new Calculator();

            calc.pressDigitKey(5);
            calc.pressBinaryOperationKey('*');
            calc.pressDigitKey(3);
            calc.pressEqualsKey();  // First equals to perform the operation 5 * 3
            calc.pressEqualsKey();  // Second equals to repeat the last operation on the result, 15 * 3

            String expected = "45";
            String actual = calc.readScreen();

            assertEquals(expected, actual);
        }




    }

