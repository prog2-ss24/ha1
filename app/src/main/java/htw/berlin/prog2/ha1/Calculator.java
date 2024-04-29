package htw.berlin.prog2.ha1;

public class Calculator {

    private String screen = "0"; // Der anfängliche Bildschirminhalt, initialisiert mit "0".
    private double latestValue; // Speichert den letzten Wert vor der Ausführung einer Operation.
    private String latestOperation = ""; // Speichert die letzte ausgewählte binäre Operation (+, -, x, /).
    private boolean newInput = false; // Flag, das bestimmt, ob die nächste Eingabe eine neue Zahl beginnt.

    /**
     * Liest den aktuellen Bildschirminhalt.
     * @return Der aktuelle Inhalt des Bildschirms als String.
     */
    public String readScreen() {
        return screen;
    }

    /**
     * Verarbeitet die Eingabe einer Zifferntaste.
     * @param digit Die gedrückte Ziffer, muss zwischen 0 und 9 sein.
     * @throws IllegalArgumentException wenn die Ziffer außerhalb des Bereichs 0-9 liegt.
     */
    public void pressDigitKey(int digit) {
        if (digit > 9 || digit < 0) throw new IllegalArgumentException("Invalid digit");

        // Setzt den Bildschirm zurück oder fügt die neue Ziffer an, wenn der Bildschirm "0" ist oder eine neue Eingabe erwartet wird.
        if ("0".equals(screen) || newInput) {
            screen = "";
            newInput = false;
        }
        screen += digit;
    }

    /**
     * Setzt den Bildschirm und alle gespeicherten Zustände zurück.
     */
    public void pressClearKey() {
        screen = "0";
        latestOperation = "";
        latestValue = 0.0;
        newInput = false;
    }

    /**
     * Verarbeitet die Eingabe einer binären Operationstaste und speichert den aktuellen Bildschirminhalt als Operand.
     * @param operation Das mathematische Symbol der Operation ("+", "-", "x", "/").
     */
    public void pressBinaryOperationKey(String operation) {
        latestValue = Double.parseDouble(screen);
        latestOperation = operation;
        newInput = true; // Signalisiert, dass die nächste Eingabe ein neuer Operand ist.
    }

    /**
     * Führt eine unäre Operation basierend auf dem aktuellen Bildschirminhalt aus und zeigt das Ergebnis an.
     * @param operation Das Symbol der unären Operation ("√", "%", "1/x").
     * @throws IllegalArgumentException bei Auswahl einer nicht unterstützten Operation.
     */
    public void pressUnaryOperationKey(String operation) {
        double input = Double.parseDouble(screen);
        double result = switch(operation) {
            case "√" -> Math.sqrt(input);
            case "%" -> input / 100;
            case "1/x" -> input == 0 ? Double.NaN : 1 / input;
            default -> throw new IllegalArgumentException("Unsupported operation");
        };

        if (Double.isInfinite(result) || Double.isNaN(result)) {
            screen = "Error";
        } else {
            screen = String.format("%.8f", result).replaceAll("0*$", ""); // Entfernen führender Nullen
            screen = screen.replaceAll("\\.$", ""); // Entfernen des Dezimalpunkts, wenn keine Dezimalzahlen folgen
        }
        newInput = true;
    }

    /**
     * Fügt einen Dezimalpunkt hinzu, wenn der aktuelle Bildschirminhalt noch keinen enthält.
     */
    public void pressDotKey() {
        if (!screen.contains(".")) {
            screen += ".";
        }
        newInput = false; // Eingaben werden angehängt und nicht den Inhalt ersetzen
    }


    /**
     * Wechselt das Vorzeichen des aktuellen Bildschirminhalts.
     */
    public void pressNegativeKey() {
        screen = screen.startsWith("-") ? screen.substring(1) : "-" + screen; // Umschalten zwischen negativem und positivem Vorzeichen.
    }

    /**
     * Berechnet das Ergebnis der zuletzt ausgewählten binären Operation mit dem gespeicherten Wert und dem aktuellen Bildschirminhalt.
     */
    public void pressEqualsKey() {
        if (latestOperation.isEmpty()) return; // Führt keine Berechnung durch, wenn keine Operation gewählt wurde.

        double operand = Double.parseDouble(screen);
        double result = switch(latestOperation) {
            case "+" -> latestValue + operand;
            case "-" -> latestValue - operand;
            case "x" -> latestValue * operand;
            case "/" -> operand == 0 ? Double.NaN : latestValue / operand;
            default -> throw new IllegalArgumentException("Unsupported operation");
        };

        if (Double.isNaN(result) || Double.isInfinite(result)) {
            screen = "Error";
        } else {
            // Hier prüfen wir, ob das Ergebnis tatsächlich eine Ganzzahl ist
            if (result == Math.floor(result)) {
                screen = String.format("%.0f", result); // Keine Dezimalstellen, wenn Ganzzahl
            } else {
                // Sonst geben wir das Ergebnis mit bis zu 8 Dezimalstellen aus
                screen = String.format("%.8f", result);
                screen = screen.replaceAll("0*$", ""); // Entfernen führender Nullen
                screen = screen.replaceAll("\\.$", ""); // Entfernen des Dezimalpunkts, wenn keine Dezimalzahlen folgen
            }
        }
        newInput = true;
    }
}

