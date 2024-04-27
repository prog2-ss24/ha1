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


    // hier weitere Tests erstellen

@Test
@DisplayName("Perioden Stellenwert < 10)")
void testStellenwerteunter10(){
    Calculator calc = new Calculator();

    calc.pressDigitKey(1);
    calc.pressDigitKey(0);
    calc.pressBinaryOperationKey("/");
    calc.pressDigitKey(3);
    calc.pressEqualsKey();
    String expected = "3.33333333";
    String actual = calc.readScreen();
    assertEquals(expected, actual);
}


@Test 
@DisplayName("Doppelter Punkt")
void Doublepoint(){
    Calculator calc = new Calculator();
    calc.pressDigitKey(1);
    calc.pressDotKey();
    calc.pressDigitKey(0);
    calc.pressDotKey();
    calc.pressDigitKey(1);
    calc.pressBinaryOperationKey("+");
    calc.pressDigitKey(1);
    calc.pressEqualsKey();
    String expected = "2.01";
    String actual = calc.readScreen();
    assertEquals(expected, actual);
}


@Test@DisplayName("Multiplikation von 0")
void multiplicationWith0(){
    Calculator calc = new Calculator();
    calc.pressDigitKey(5);
    calc.pressDigitKey(0);
    calc.pressBinaryOperationKey("x");
    calc.pressDigitKey(0); 
    calc.pressEqualsKey();
    String expected = "0";
    String actual = calc.readScreen();
    assertEquals(expected, actual);
}

@Test@DisplayName("Dreifach Operation")
void trippleoperation(){
    Calculator calc = new Calculator();
    calc.pressDigitKey(2);
    calc.pressBinaryOperationKey("+");
    calc.pressDigitKey(2);
    calc.pressBinaryOperationKey("+");
    calc.pressDigitKey(2);
    calc.pressEqualsKey();
    String expected = "6";
    String actual = calc.readScreen();
                assertEquals(expected, actual);
    }

@Test @DisplayName("Addition von negativen Zahlen")
void sumOfNegativIntegers(){
    Calculator calc = new Calculator();
        calc.pressBinaryOperationKey("-");
    calc.pressDigitKey(1);
    calc.pressBinaryOperationKey("+");
    calc.pressDigitKey(4);
    calc.pressEqualsKey();
String expected= "3";
String actual = calc.readScreen();
assertEquals(expected, actual);
}

@Test
@DisplayName("Addieren von negativem Wert")
void additionOfNegativInt(){
    Calculator calc = new Calculator(); 
    calc.pressBinaryOperationKey("-");
calc.pressDigitKey(2);
calc.pressBinaryOperationKey("+");
calc.pressDigitKey(5);
calc.pressEqualsKey();
String expected = "3";
String actual = calc.readScreen();
assertEquals(expected, actual);

}

    }

        


