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

    //Eingabe löschen

    @Test
    @DisplayName("Should rase input")
    void testClearKey() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDigitKey(6);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(8);
        calc.pressClearKey();
        String expected = "0";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    // Bug 1
    @Test
    @DisplayName("Should be able to perform multi-operator calculations")
    void testMultiplicationAndAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(8);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(4);
        calc.pressBinaryOperationKey("x");
        calc.pressDigitKey(2);
        calc.pressEqualsKey();
        

        String expected = "34";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
}

    @Test
    @DisplayName("Should output a single percent of the associated value and be processed by the underlying calculation")
    void testPercentageKey() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(8);
        calc.pressDigitKey(1);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(1);
        calc.pressDigitKey(1);
        calc.pressUnaryOperationKey("√");
        //calc.pressEqualsKey();
        

        String expected = "112";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    
}


