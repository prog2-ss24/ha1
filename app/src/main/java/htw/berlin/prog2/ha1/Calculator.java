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

    private double lastInputValue;// Neues Feld zum Speichern des zuletzt eingegebenen Werts

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
     * Empfängt den Befehl der gedrückten "=" Taste und führt die Berechnung basierend auf der zuletzt ausgeführten Operation
     * und dem zuletzt eingegebenen Wert durch. Diese Methode handhabt auch Fehlersituationen wie die Division durch Null.
     * Wenn keine Operation vor der "=" Taste ausgeführt wurde, bleibt der Zustand unverändert.
     *
     * @throws IllegalArgumentException wenn eine ungültige Operation übergeben wird.
     */
    public void pressEqualsKey() {
        double currentInput = Double.parseDouble(screen);

        if (latestOperation.isEmpty()) return;  // Wenn keine vorherige Operation vorhanden ist, führe nichts aus.

        if (!latestOperation.isEmpty() && lastInputValue != 0) {
            // Wenn ein letzter Eingabewert vorhanden und nicht 0 ist, verwende diesen Wert für die Berechnung.
            currentInput = lastInputValue;
        } else {
            // Andernfalls aktualisiere den letzten Eingabewert mit dem aktuellen Wert auf dem Bildschirm für zukünftige Operationen.
            lastInputValue = currentInput;
        }

        double result = 0;
        boolean isError = false;
        switch (latestOperation) {
            case "+":
                result = latestValue + currentInput;
                break;
            case "-":
                result = latestValue - currentInput;
                break;
            case "x":
                result = latestValue * currentInput;
                break;
            case "/":
                if (currentInput == 0) {
                    isError = true;
                } else {
                    result = latestValue / currentInput;
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid operation.");
        }

        if (isError) {
            screen = "Error";
        } else {
            latestValue = result;  // Aktualisiere den letzten berechneten Wert für die nächste Verwendung.
            screen = formatResult(result);// Formatieren und anzeigen des Ergebnisses.
        }
    }

    /**
     * Formatieren des Ergebnisses der Berechnung für die Anzeige auf dem Bildschirm.
     * Entfernt unnötige Nullen und Punkte bei Bedarf, um das Ergebnis sauber darzustellen.
     *
     * @param result das zu formatierende Ergebnis der Berechnung.
     * @return formatierter String des Ergebnisses.
     */
    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%.10f", result).replaceAll("0*$", "").replaceAll("\\.$", "");
        }
    }
}

