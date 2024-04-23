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

    /**
     * Eine Flagge, die den Zustand der Subtraktionsoperation speichert.
     * Wenn die Flagge auf true gesetzt ist, wird die Subtraktion indirekt durchgeführt,
     * indem der Wert des Bildschirms vom zuvor gespeicherten Wert subtrahiert wird.
     * Wenn die Flagge auf false gesetzt ist, wird die Subtraktion direkt durchgeführt,
     * indem der zuvor gespeicherte Wert vom Wert des Bildschirms subtrahiert wird.
     */
    private boolean flag = false;


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
     *
     * Die Überprüfung if(digit > 9 || digit < 0) wurde entfernt, da die Methode nur aufgerufen wird, wenn eine Zifferntaste gedrückt wird, und die übergebene Ziffer digit daher bereits zwischen 0 und 9 liegt.
     * Somit ist diese Überprüfung nicht mehr notwendig und wurde entfernt, um den Code zu vereinfachen und die Lesbarkeit zu verbessern.
     */
    public void pressDigitKey(int digit) {

        if(screen.equals("0") || latestValue == Double.parseDouble(screen)) {
            screen = "";
        }

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
        flag = false;
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
     *
     * Wenn das Ergebnis der Operation den Wert "Infinity" ergibt, wird der Bildschirmtext ebenfalls auf "Error" gesetzt,
     * um anzuzeigen, dass eine Division durch Null durchgeführt wurde.
     * Wenn das Ergebnis der Operation mit einer Dezimalstelle ".0" endet, wird diese Dezimalstelle entfernt, um
     * eine überschüssige Darstellung zu vermeiden.
     */
    public void pressUnaryOperationKey(String operation) {
        pressBinaryOperationKey(operation);
        var result = switch(operation) {
            case "√" -> Math.sqrt(Double.parseDouble(screen));
            case "%" -> Double.parseDouble(screen) / 100;
            case "1/x" -> 1 / Double.parseDouble(screen);
            default -> throw new IllegalArgumentException();
        };
        screen = Double.toString(result);
        if(screen.equals("NaN")) screen = "Error";
        if(screen.equals("Infinity")) screen = "Error";
        if(screen.endsWith(".0")) screen = screen.substring(0,screen.length()-2);
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
     * Empfängt den Befehl der gedrückten "="-Taste.
     * Wurde zuvor keine Operationstaste gedrückt, passiert nichts.
     * Wurde zuvor eine binäre Operationstaste gedrückt und zwei Operanden eingegeben, wird das
     * Ergebnis der Operation angezeigt. Falls hierbei eine Division durch Null auftritt, wird "Error" angezeigt.
     * Wird die Taste weitere Male gedrückt (ohne andere Tasten dazwischen), so wird die letzte
     * Operation (ggf. inklusive letztem Operand) erneut auf den aktuellen Bildschirminhalt angewandt
     * und das Ergebnis direkt angezeigt.
     *
     * Die Methode verwendet eine Switch-Anweisung, um basierend auf der zuletzt gedrückten
     * Operation die entsprechende Berechnung durchzuführen. Zusätzlich gibt es ein Boolean-Flag
     * (flag), das verwendet wird, um den Subtraktionsmodus zu steuern und sicherzustellen, dass
     * die richtige Reihenfolge der Operanden bei wiederholtem Drücken der "="-Taste eingehalten wird.
     *
     */
    public void pressEqualsKey() {
        var result = 0.0;

        switch(latestOperation) {
            case "+" -> {
                result = this.latestValue + Double.parseDouble(screen);
                pressBinaryOperationKey("+");
            }
            case "-" -> {
                if (flag) {
                    result = Double.parseDouble(screen) - latestValue;
                    flag = false;
                }
                else {
                    result = latestValue - Double.parseDouble(screen);
                    flag = true;
                }
                pressBinaryOperationKey("-");

            }
            case "x" -> {
                result = latestValue * Double.parseDouble(screen);
                pressBinaryOperationKey("x");
            }
            case "/" -> {
                result = latestValue / Double.parseDouble(screen);
                pressBinaryOperationKey("/");
            }
            default -> throw new IllegalArgumentException();
        };

        screen = Double.toString(result);

        if(screen.equals("Infinity")) screen = "Error";
        if(screen.endsWith(".0")) screen = screen.substring(0,screen.length()-2);
        if(screen.contains(".") && screen.length() > 11) screen = screen.substring(0, 10);
    }
}

