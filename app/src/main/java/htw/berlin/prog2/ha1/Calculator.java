package htw.berlin.prog2.ha1;

import java.util.Locale;

public class Calculator {

    // Bildschirm zeigt den aktuellen Wert an
    private String screen = "0";

    // Hält den neuesten Wert für Berechnungen
    private double latestValue;

    // Speichert die zuletzt ausgeführte Operation
    private String latestOperation = "";

    // Gibt den aktuellen Bildschirmwert zurück
    public String readScreen() {
        return screen;
    }

    // Fügt eine Ziffer zum Bildschirmwert hinzu
    public void pressDigitKey(int digit) {
        if(digit > 9 || digit < 0) throw new IllegalArgumentException();

        // Setzt den Bildschirm auf eine leere Zeichenkette, wenn der Wert 0 ist oder der aktuelle Wert mit dem neuesten Wert übereinstimmt
        if(screen.equals("0") || latestValue == Double.parseDouble(screen)) screen = "";

        screen = screen + digit;
    }

    // Variable, um zu verfolgen, ob die Taste "Clear" bereits gedrückt wurde
    private boolean pressed = false;

    // Löscht den Bildschirm oder alle Berechnungsdaten
    public void pressClearKey() {
        if (pressed == false) {
            screen = "0";
            pressed = true;
        } else {
            screen = "0";
            latestOperation = "";
            latestValue = 0.0;
        }
    }

    // Führt eine binäre Operation aus
    public void pressBinaryOperationKey(String operation)  {
        latestValue = Double.parseDouble(screen);
        latestOperation = operation;
    }

    // Führt eine unäre Operation aus, basierend auf dem übergebenen Operator
    public void pressUnaryOperationKey(String operation) {
        latestValue = Double.parseDouble(screen); // Speichert den aktuellen Wert für die spätere Berechnung
        latestOperation = operation; // Speichert die zuletzt ausgeführte Operation

        // Überprüft den übergebenen Operator und führt die entsprechende Operation aus
        if (operation.equals("%")) {
            // Berechnet den Prozentsatz, indem die aktuelle Zahl durch 100 geteilt wird
            double percentage = latestValue / 100;
            screen = String.format("%.8f", percentage); // Formatierung auf acht Dezimalstellen
        } else if (operation.equals("√")) {
            if (latestValue < 0) {
                screen = "Error"; // Gibt "Error" zurück, wenn die Zahl negativ ist
            } else {
                double result = Math.sqrt(latestValue); // Berechnet die Quadratwurzel der aktuellen Zahl
                screen = String.format(Locale.US, "%.8f", result); // Formatierung auf acht Dezimalstellen mit US-Locale
            }
        } else if (operation.equals("1/x")) {
            // Berechnet den Kehrwert der aktuellen Zahl
            double result = 1 / latestValue;
            // Überprüft, ob das Ergebnis eine Ganzzahl ist, und formatiert es entsprechend
            if (result == (long) result) {
                screen = String.format("%d", (long) result); // Formatierung als Ganzzahl
            } else {
                screen = String.format("%.8f", result); // Formatierung auf acht Dezimalstellen
            }
        } else {
            throw new IllegalArgumentException("Invalid operation: " + operation); // Wirft eine Ausnahme für ungültige Operationen
        }
    }


    // Fügt einen Dezimalpunkt zum Bildschirmwert hinzu, wenn noch keiner vorhanden ist
    public void pressDotKey() {
        if(!screen.contains(".")) screen = screen + ".";
    }

    // Ändert das Vorzeichen des Bildschirmwerts
    public void pressNegativeKey() {
        screen = screen.startsWith("-") ? screen.substring(1) : "-" + screen;
    }

    // Führt die Berechnung aus und aktualisiert den Bildschirmwert
    public void pressEqualsKey() {
        var result = switch(latestOperation) {
            case "+" -> latestValue + Double.parseDouble(screen);
            case "-" -> latestValue - Double.parseDouble(screen);
            case "x" -> latestValue * Double.parseDouble(screen);
            case "/" -> latestValue / Double.parseDouble(screen);
            default -> throw new IllegalArgumentException();
        };
        screen = Double.toString(result);

        // Setzt den Bildschirmwert auf "Error", wenn das Ergebnis unendlich ist
        if(screen.equals("Infinity")) screen = "Error";

        // Entfernt ".0" am Ende des Bildschirmwerts, wenn es eine Ganzzahl darstellt
        if(screen.endsWith(".0")) screen = screen.substring(0,screen.length()-2);

        // Begrenzt die Länge des Bildschirmwerts auf 10 Zeichen, wenn er eine Dezimalstelle enthält
        if(screen.contains(".") && screen.length() > 11) screen = screen.substring(0, 10);
    }
}
