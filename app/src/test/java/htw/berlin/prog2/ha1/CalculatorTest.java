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
}
git commit -m     "New Green Test Percentage Calculation"
git log 

    @Test
    @DisplayName("should display result after multiplying two numbers with the percentage operation key")
    void testPercentageCalculation() {
      
        Calculator calc = new Calculator();

       
        calc.pressDigitKey(6);
        calc.pressBinaryOperationKey("x"); 
        calc.pressDigitKey(5);
        calc.pressDigitKey(0);
        calc.pressUnaryOperationKey("%");

       
        String expected = "3";
        String actual = calc.readScreen();
        assertEquals(expected, actual);
    }
}
git commit -m  "New Red Test Input Validation Error"
git log
    
    @Test
    @DisplayName("should ensure screen remains unchanged, when there is an invalid input") 
    void testInputValidationError() {
       
        Calculator calculator = new Calculator();

        try {
            calculator.pressDigitKey('A');
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("0", calculator.readScreen()); 
            assertEquals(expected, actual);
          
        
}
       
git commit -m "Fix for the New Red test Input Validation Error"
git log    

    
    public void pressDigitKey(int digit) {
        if (digit < 0 || digit > 9) {
         
            System.out.println("Invalid Input");
            return; 
        }
        
        if(screen.equals("0") || latestValue == Double.parseDouble(screen)) screen = "";
        screen = screen + digit;
    }
}


git commit -m "New Red Test Round Off Error"
git log    

    @Test
    @DisplayName("should be able to give a rounded result")
    void testRoundOffError() {
    
        Calculator calculator = new Calculator();

       
        calculator.pressDigitKey(1);
        calculator.pressDotKey(.); 
        calculator.pressDigitKey(0);
        calculator.pressDigitKey(0); 
        calculator.pressBinaryOperationKey("/"); 
        calculator.pressDigitKey(3); 
        calculator.pressEqualsKey(); 

        
        assertEquals("0.3333333333", calculator.readScreen()); 
        assertEquals(expected, actual); 
}


git commit -m "Fix for the New Red Test Round Off Error"
git log    

import java.text.DecimalFormat;

  class Calculator {
    
    
    public void pressEqualsKey() {
        double result = switch(latestOperation) {
           
            default -> throw new IllegalArgumentException();
        };
        
      
        DecimalFormat df = new DecimalFormat("#.##########");
        screen = df.format(result);
    }
}
