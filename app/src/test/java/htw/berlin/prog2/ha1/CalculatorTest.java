package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;  //compares if exactly and really equal

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
    @Test
    @DisplayName("should correctly add negative numbers")
    void testAddNegatives() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(9);
        calc.pressNegativeKey();
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(9);
        calc.pressNegativeKey();
        calc.pressEqualsKey(); 
    
        String expected = "-18"; 
        String actual = calc.readScreen(); 
        assertEquals(expected, actual); 
    }
    @Test
    @DisplayName("should display 0 after pressing clear key once")
    void testClearKey(){
        Calculator calc = new Calculator();
        calc.pressDigitKey(9);
        calc.pressNegativeKey();
        calc.pressClearKey();

        String expected = "0";
        String actual = calc.readScreen();
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("should correctly clear cache after pressing clear key twice")
    void testClearKeyAll() {
    Calculator calc = new Calculator();
    calc.pressDigitKey(9);
    calc.pressNegativeKey();
    calc.pressDigitKey(9);
    calc.pressClearKeyTwice(); 

    assertEquals(0.0, calc.getLatestValue());
    assertEquals("", calc.getLatestOperation());

    
}
    @Test
    @DisplayName("should correctly add 3 positive numbers")
    void testMultipleAdditions () {
    Calculator calc = new Calculator();
    calc.pressDigitKey(9);
    calc.pressBinaryOperationKey("+");
    calc.pressDigitKey(9);
    calc.pressBinaryOperationKey("+");
    calc.pressDigitKey(9);
    calc.pressEqualsKey();

    String expected = "27";
    String actual = calc.readScreen();
    assertEquals(expected, actual);
}
    @Test
    @DisplayName("max amount of one period allowed")
    void testPeriods(){
    Calculator calc = new Calculator();
    calc.pressDigitKey(9);
    calc.pressDotKey();
    calc.pressDotKey();
    calc.pressDotKey();
    calc.pressDotKey();

    String expected = "9.";
    String actual = calc.readScreen();
    assertEquals(expected, actual);
    }
    @Test
    @DisplayName("equal key multiple times should keep using same operation on result")
    void testEquals (){
    Calculator calc = new Calculator();
    calc.pressDigitKey(9);
    calc.pressBinaryOperationKey("+");
    calc.pressDigitKey(9);
    calc.pressEqualsKey();
    calc.pressEqualsKey();
    calc.pressEqualsKey();

    String expected = "36";
    String actual = calc.readScreen();
    assertEquals(expected, actual);
}
    @Test
    @DisplayName("should correclty multiply 2 numbers")
    void multiply (){
    Calculator calc = new Calculator();
    calc.pressDigitKey(9);
    calc.pressBinaryOperationKey("x");
    calc.pressDigitKey(9);
    calc.pressEqualsKey();

    String expected = "81";
    String actual = calc.readScreen();
    assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should correctly divide 2 numbers")
    void divide(){
    Calculator calc = new Calculator();
    calc.pressDigitKey(9);
    calc.pressBinaryOperationKey("/");
    calc.pressDigitKey(9);
    calc.pressEqualsKey();

    String expected = "1";
    String actual = calc.readScreen();
    assertEquals(expected, actual);
    }

    @Test
    @DisplayName("check if can enter double digit nr")  //fails as it should
    void doubleNR (){
    Calculator calc = new Calculator();
    calc.pressDigitKey(13);
    calc.pressBinaryOperationKey("+");
    calc.pressDigitKey(1);
    calc.pressEqualsKey();

    String expected = "14";
    String actual = calc.readScreen();
    assertEquals(expected, actual);
}

}

