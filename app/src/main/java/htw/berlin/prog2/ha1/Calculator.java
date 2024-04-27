package htw.berlin.prog2.ha1;

import java.math.BigDecimal;
import java.math.RoundingMode;


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

    /**
     * @return den aktuellen Bildschirminhalt als String
     */
    public String readScreen() {
        return screen;
    }

    /**
     * Empfängt den Wert einer gedrückten Zifferntaste. Da man nur eine Taste auf einmal
     * drücken kann muss der Wert positiv und einstellig sein und zwischen 0 und 9 liegen.
     * Führt in jedem Fall dazu, dass die gerade gedrückte Ziffer auf dem Bildschirm angezeigt
     * oder rechts an die zuvor gedrückte Ziffer angehängt angezeigt wird.
     * @param digit Die Ziffer, deren Taste gedrückt wurde
     */
    public void pressDigitKey(int digit) {
        if(digit > 9 || digit < 0) throw new IllegalArgumentException();

        if(screen.equals("0") || latestValue == Double.parseDouble(screen)) screen = "";

        screen = screen + digit;
    }

    /**
     * Empfängt den Befehl der C- bzw. CE-Taste (Clear bzw. Clear Entry).
     * Einmaliges Drücken der Taste löscht die zuvor eingegebenen Ziffern auf dem Bildschirm
     * so dass "0" angezeigt wird, jedoch ohne zuvor zwischengespeicherte Werte zu löschen.
     * Wird daraufhin noch einmal die Taste gedrückt, dann werden auch zwischengespeicherte
     * Werte sowie der aktuelle Operationsmodus zurückgesetzt, so dass der Rechner wieder
     * im Ursprungszustand ist.
     */
    public void pressClearKey() {
        screen = "0";
        latestOperation = "";
        latestValue = 0.0;
    }

    /**
     * Empfängt den Wert einer gedrückten binären Operationstaste, also eine der vier Operationen
     * Addition, Substraktion, Division, oder Multiplikation, welche zwei Operanden benötigen.
     * Beim ersten Drücken der Taste wird der Bildschirminhalt nicht verändert, sondern nur der
     * Rechner in den passenden Operationsmodus versetzt.
     * Beim zweiten Drücken nach Eingabe einer weiteren Zahl wird direkt des aktuelle Zwischenergebnis
     * auf dem Bildschirm angezeigt. Falls hierbei eine Division durch Null auftritt, wird "Error" angezeigt.
     * @param operation "+" für Addition, "-" für Substraktion, "x" für Multiplikation, "/" für Division
     */
    public void pressBinaryOperationKey(String operation)  {
        latestValue = Double.parseDouble(screen);
        latestOperation = operation;
    }

    /**
     * Empfängt den Wert einer gedrückten unären Operationstaste, also eine der drei Operationen
     * Quadratwurzel, Prozent, Inversion, welche nur einen Operanden benötigen.
     * Beim Drücken der Taste wird direkt die Operation auf den aktuellen Zahlenwert angewendet und
     * der Bildschirminhalt mit dem Ergebnis aktualisiert.
     * @param operation "√" für Quadratwurzel, "%" für Prozent, "1/x" für Inversion
     */
    public void pressUnaryOperationKey(String operation) {
        latestValue = Double.parseDouble(screen);
        latestOperation = operation;
        var result = switch(operation) {
            case "√" -> Math.sqrt(Double.parseDouble(screen));
            case "%" -> Double.parseDouble(screen) / 100;
            case "1/x" -> 1 / Double.parseDouble(screen);
            default -> throw new IllegalArgumentException();
        };
        screen = Double.toString(result);
        if(screen.equals("NaN")) screen = "Error";
        if(screen.contains(".") && screen.length() > 11) screen = screen.substring(0, 10);

    }

    /**
     * Empfängt den Befehl der gedrückten Dezimaltrennzeichentaste, im Englischen üblicherweise "."
     * Fügt beim ersten Mal Drücken dem aktuellen Bildschirminhalt das Trennzeichen auf der rechten
     * Seite hinzu und aktualisiert den Bildschirm. Daraufhin eingegebene Zahlen werden rechts vom
     * Trennzeichen angegeben und daher als Dezimalziffern interpretiert.
     * Beim zweimaligem Drücken, oder wenn bereits ein Trennzeichen angezeigt wird, passiert nichts.
     */
    public void pressDotKey() {
        if(!screen.contains(".")) screen = screen + ".";
    }

    /**
     * Empfängt den Befehl der gedrückten Vorzeichenumkehrstaste ("+/-").
     * Zeigt der Bildschirm einen positiven Wert an, so wird ein "-" links angehängt, der Bildschirm
     * aktualisiert und die Inhalt fortan als negativ interpretiert.
     * Zeigt der Bildschirm bereits einen negativen Wert mit führendem Minus an, dann wird dieses
     * entfernt und der Inhalt fortan als positiv interpretiert.
     */
    public void pressNegativeKey() {
        screen = screen.startsWith("-") ? screen.substring(1) : "-" + screen;
    }

    /**
     * Verarbeitet die "=" Taste des Taschenrechners.
     * Diese Methode versucht, die zuvor eingestellte binäre Operatoraktion auszuführen, wenn die "=" Taste gedrückt wird. Wenn zuvor kein Operator ausgewählt wurde, erfolgt keine Aktion.
     * Wenn zwei Operanden eingegeben wurden und ein gültiger Operator ausgewählt wurde, wird das Ergebnis berechnet und angezeigt:
     * - Für Addition, Subtraktion und Multiplikation wird die Berechnung direkt ausgeführt.
     * - Bei der Division, wenn der Nenner null ist, wird "Error" angezeigt.
     * Wenn die "=" Taste erneut gedrückt wird, wird die letzte Operation mit dem aktuellen Anzeigeergebnis und dem vorherigen Operator wiederholt.
     * Das Ergebnis wird mit BigDecimal berechnet, um hohe Genauigkeit zu gewährleisten, und vor der Anzeige wird es als String formatiert, der maximal zwei Dezimalstellen hat. Wenn die Länge des Ergebnisstrings mehr als 10 Zeichen beträgt, wird sie auf die ersten 10 Zeichen gekürzt.
     * Hinweis: Wenn während der Berechnung Fehler wie Division durch Null auftreten, wird eine Fehlermeldung angezeigt und es kann eine IllegalArgumentException oder ArithmeticException ausgelöst werden.
     *
     * @throws IllegalArgumentException wenn die durchgeführte Operation ungültig ist
     * @throws ArithmeticException wenn das Ergebnis nicht genau in eine Ganzzahl umgewandelt werden kann
     */
    public void pressEqualsKey() {
        if (latestOperation.isEmpty()) {
            return; // Wenn keine Operatoren vorhanden sind, wird direkt zurückgegeben
        }

        BigDecimal result;
        boolean isDecimalOperation = screen.contains(".") || String.valueOf(latestValue).contains(".");

        BigDecimal currentScreenValue = new BigDecimal(screen);
        BigDecimal latestBigDecimalValue = new BigDecimal(String.valueOf(latestValue));

        switch (latestOperation) {
            case "+":
                result = latestBigDecimalValue.add(currentScreenValue);
                break;
            case "-":
                result = latestBigDecimalValue.subtract(currentScreenValue);
                break;
            case "x":
                result = latestBigDecimalValue.multiply(currentScreenValue);
                break;
            case "/":
                if (currentScreenValue.compareTo(BigDecimal.ZERO) == 0) {
                    screen = "Error";
                    return;
                }
                result = latestBigDecimalValue.divide(currentScreenValue, 10, RoundingMode.HALF_UP);
                break;
            default:
                throw new IllegalArgumentException("Invalid operation");
        }

        if (isDecimalOperation) {
            // Umgang mit Ergebnissen von Operationen mit Dezimalzahlen
            screen = result.stripTrailingZeros().toPlainString();
        } else {
            // Keine Dezimalstellen, möglichst als ganze Zahl anzeigen
            try {
                screen = result.toBigIntegerExact().toString();
            } catch (ArithmeticException e) {
                // Wenn eine exakte Umwandlung in eine ganze Zahl nicht möglich ist (z. B. weil das Ergebnis eine Dezimalzahl ist), werden zwei Dezimalstellen reserviert.
                screen = result.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
            }
        }

        if (screen.length() > 10) {
            screen = screen.substring(0, 10); // Kontrolle der Ausgabelänge
        }

        latestValue = new BigDecimal(screen).doubleValue(); // Aktualisieren Sie den letzten Wert für nachfolgende Operationen
    }
}
