package htw.berlin.prog2.ha1;
import java.util.ArrayList;
import java.util.List;

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

    private ArrayList<Double> saveLastValues = new ArrayList<>();
    private ArrayList<String> saveLastOperation = new ArrayList<>();

    /**
     * @return den aktuellen Bildschirminhalt als String
     */
    public String readScreen() {
        return screen;
    }

    /**
     * Empfängt den Wert einer gedrückten Zifferntaste. Da man nur eine Taste auf einmal
     * drücken kann, muss der Wert positiv und einstellig sein und zwischen 0 und 9 liegen.
     * Führt in jedem Fall dazu, dass die gerade gedrückte Ziffer auf dem Bildschirm angezeigt
     * oder rechts an die zuvor gedrückte Ziffer angehängt angezeigt wird.
     * @param digit Die Ziffer, deren Taste gedrückt wurde
     */
    public void pressDigitKey(int digit) {
        if(digit > 9 || digit < 0) throw new IllegalArgumentException();
        if(screen.equals("0") || Double.parseDouble(screen) == 0) screen = "";
        screen = screen + digit;
    }

    /**
     * Empfängt den Befehl der C- bzw. CE-Taste (Clear bzw. Clear Entry).
     * Einmaliges Drücken der Taste löscht die zuvor eingegebenen Ziffern auf dem Bildschirm,
     * sodass "0" angezeigt wird, jedoch ohne zuvor zwischengespeicherte Werte zu löschen.
     * Wird daraufhin noch einmal die Taste gedrückt, dann werden auch zwischengespeicherte
     * Werte sowie der aktuelle Operationsmodus zurückgesetzt, sodass der Rechner wieder
     * im Ursprungszustand ist.
     */
    public void pressClearKey() {
        screen = "0";
        latestOperation = "";
        latestValue = 0.0;
    }

    /**
     * Empfängt den Wert einer gedrückten binären Operationstaste, also eine der vier Operationen
     * Addition, Subtraktion, Division, oder Multiplikation, welche zwei Operanden benötigen.
     * Beim ersten Drücken der Taste wird der Bildschirminhalt nicht verändert, sondern nur der
     * Rechner in den passenden Operationsmodus versetzt.
     * Beim zweiten Drücken nach Eingabe einer weiteren Zahl wird direkt des aktuelle Zwischenergebnis
     * auf dem Bildschirm angezeigt. Falls hierbei eine Division durch Null auftritt, wird "Error" angezeigt.
     * @param operation "+" für Addition, "-" für Subtraktion, "x" für Multiplikation, "/" für Division
     */
    public void pressBinaryOperationKey(String operation)  {
        saveLastValues.add(Double.parseDouble(screen));
        saveLastOperation.add(operation);
        screen = "0";
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
        double result = 0.0;
        switch(operation) {
            case "√" -> result = Math.sqrt(Double.parseDouble(screen));
            case "%" -> result = Double.parseDouble(screen) / 100;
            case "1/x" -> result = 1 / Double.parseDouble(screen);
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
     * Empfängt den Befehl der gedrückten "="-Taste.
     * Wurde zuvor keine Operationstaste gedrückt, passiert nichts.
     * Wurde zuvor eine binäre Operationstaste gedrückt und zwei Operanden eingegeben, wird das
     * Ergebnis der Operation angezeigt. Falls hierbei eine Division durch Null auftritt, wird "Error" angezeigt.
     * Wird die Taste weitere Male gedrückt (ohne andere Tasten dazwischen), so wird die letzte
     * Operation (ggf. inklusive letztem Operand) erneut auf den aktuellen Bildschirminhalt angewandt
     * und das Ergebnis direkt angezeigt.
     */

    public void pressEqualsKey() {
        saveLastValues.add(Double.parseDouble(screen));
        double result;
        try {
            result = checkOperation();
            if (result % 1 == 0) {
                screen = Integer.toString((int) result);
            } else {
                screen = Double.toString(result);
            }
            //if (screen.equals("Infinity") || screen.equals("NaN")) {screen = "Error";}
        }catch (ArithmeticException e) {
            screen = "Error";
        }
    }


    public double checkOperation() { //2 Durchgänge - 1. Erledigt alle "Punkt"(multiplikation/division) Aufgaben
        List<Double> zwischenWert = new ArrayList<>();
        List<String> restOperationen = new ArrayList<>();

        double aktWert = saveLastValues.get(0);
        for (int i = 0; i < saveLastOperation.size(); i++) {
            String operation = saveLastOperation.get(i);
            double nextWert = saveLastValues.get(i + 1);

            switch (operation) {
                case "x":
                    aktWert *= nextWert;
                    break;
                case "/":
                    if (nextWert == 0) {
                        throw new ArithmeticException("Division durch null!");
                    }else{aktWert /= nextWert;}
                    break;
                default:
                    zwischenWert.add(aktWert);
                    restOperationen.add(operation);
                    aktWert = nextWert;
                    break;
            }
        }
        zwischenWert.add(aktWert);

        double result = zwischenWert.get(0); // 2. Durchgang - Erledigt alle "Strich"(Addition/Subtraktion) Aufgaben
        for (int i = 0; i < restOperationen.size(); i++) {
            String operation = restOperationen.get(i);
            double nextWert = zwischenWert.get(i + 1);
            if (operation.equals("+")) {
                result += nextWert;
            } else if (operation.equals("-")) {
                result -= nextWert;
            } else {
                throw new IllegalArgumentException("Falsche Operation");
            }
        }
        return result;
    }
}