package htw.berlin.prog2.ha1;

/**
 * Eine Klasse, die das Verhalten des Online Taschenrechners imitiert, welcher auf
 * https://www.online-calculator.com/ aufgerufen werden kann (ohne die Memory-Funktionen)
 * und dessen Bildschirm bis zu zehn Ziffern plus einem Dezimaltrennzeichen darstellen kann.
 * Enthält mit Absicht noch diverse Bugs oder unvollständige Funktionen.
 */
public class Calculator {

    private String screen = "0";
    private double latestValue;
    private String latestOperation = "";

    public String readScreen() {
        return screen;
    }

    public void pressDigitKey(int digit) {
        if (digit > 9 || digit < 0) throw new IllegalArgumentException();
        if (screen.equals("0") || latestValue == Double.parseDouble(screen)) screen = "";
        if (screen.length() < 10) {
            screen = screen + digit;
        }
    }

    public void pressClearKey() {
        screen = "0";
        latestOperation = "";
        latestValue = 0.0;
    }

    public void pressBinaryOperationKey(String operation) {
        latestValue = Double.parseDouble(screen);
        latestOperation = operation;
    }

    public void pressUnaryOperationKey(String operation) {
        latestValue = Double.parseDouble(screen);
        latestOperation = operation;

        if (latestValue < 0 && operation.equals("√")) {
            screen = "Error";
        } else {
            var result = switch (operation) {
                case "√" -> Math.sqrt(latestValue);
                case "%" -> latestValue / 100;
                case "1/x" -> 1 / latestValue;
                default -> throw new IllegalArgumentException();
            };
            screen = Double.toString(result);
            if (screen.equals("Infinity")) screen = "Error";
            if (screen.equals("NaN")) screen = "Error";
            if (screen.contains(".") && screen.length() > 11) screen = screen.substring(0, 10);
        }
    }

    public void pressDotKey() {
        if (!screen.contains(".")) screen = screen + ".";
    }

    public void pressNegativeKey() {
        screen = screen.startsWith("-") ? screen.substring(1) : "-" + screen;
    }

    public void pressEqualsKey() {
        // Überprüfen, welche Operation zuletzt ausgewählt wurde (z.B. +, -, x, /)
        double result = switch (latestOperation) {
            case "+" -> latestValue + Double.parseDouble(screen); // Addition
            case "-" -> latestValue - Double.parseDouble(screen); // Subtraktion
            case "x" -> latestValue * Double.parseDouble(screen); // Multiplikation
            case "/" -> {
                // Division
                double divisor = Double.parseDouble(screen);
                if (divisor == 0.0) {
                    // Wenn der Divisor (die Zahl, durch die geteilt wird) 0 ist, gebe unendlich zurück (Division durch Null)
                    yield Double.POSITIVE_INFINITY;
                } else {
                    // Andernfalls berechne das Ergebnis der Division
                    yield latestValue / divisor;
                }
            }
            default -> throw new IllegalArgumentException("Ungültige Operation"); // Fehler bei ungültiger Operation
        };

        // Nach der Berechnung des Ergebnisses überprüfen wir, ob das Ergebnis unendlich ist (z.B. bei Division durch Null)
        if (Double.isInfinite(result)) {
            screen = "Error"; // Wenn das Ergebnis unendlich ist, zeige "Error" auf dem Bildschirm an
        } else {
            // Andernfalls formatiere das Ergebnis und zeige es auf dem Bildschirm an
            screen = formatResult(result);
        }
    }
    // Hilfsmethode zur Formatierung des Ergebnisses
    private String formatResult(double result) {
        String formattedResult = Double.toString(result);
        if (formattedResult.endsWith(".0")) {
            // Entferne das ".0" am Ende, falls vorhanden
            return formattedResult.substring(0, formattedResult.length() - 2);
        } else if (formattedResult.contains(".") && formattedResult.length() > 11) {
            // Begrenze die Anzahl der angezeigten Zeichen, um eine Überlaufanzeige zu vermeiden
            return formattedResult.substring(0, 10);
        }
        return formattedResult; // Gib das formatierte Ergebnis zurück
    }

}
// neuer Versuch andere fixes